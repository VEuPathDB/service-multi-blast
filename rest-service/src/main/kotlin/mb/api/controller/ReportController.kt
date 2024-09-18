package mb.api.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.ForbiddenException
import mb.api.controller.resources.Reports
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.service.http.report.BlastReportService
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.Response
import mb.api.model.ContentRange
import mb.api.service.http.report.BlastReportService.convert
import mb.api.service.http.report.DiamondReportService
import mb.api.service.http.wrap
import mb.lib.query.BlastDBManager
import mb.lib.query.model.DiamondConfig
import mb.lib.report.ReportManager
import mb.lib.util.logger
import mb.lib.workspace.DiamondWorkspace
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

@Authenticated(allowGuests = true)
class ReportController(@Context private val request: ContainerRequest): Reports
{
  private val logger = logger()

  override fun getAllReports(jobID: String?): List<ReportResponse> =
    if (jobID != null) {
      hashIDorThrow(jobID, ::NotFoundException).let { jobID ->
        if (isDiamondQueryJob(jobID)) {
          listOf(DiamondReportService.getReportInfo(jobID))
        } else {
          BlastReportService.listReports(jobID, request.requireUserID())
        }
      }
    } else {
      try {
        ReportManager.getAllReportsForUser(request.requireUserID()).map { it.convert() }
      } catch (e: Exception) {
        throw e.wrap()
      }
    }

  override fun getReport(reportID: String) =
    hashIDorThrow(reportID, ::NotFoundException).let { jobID ->
      if (isDiamondQueryJob(jobID))
        DiamondReportService.getReportInfo(jobID)
      else
        BlastReportService.getReport(jobID, request.requireUserID())
    }

  override fun newReport(req: ReportRequest) =
    if (isDiamondQueryJob(req.jobID))
      throw ForbiddenException()
    else
      BlastReportService.submitReport(req, request.requireUserID())

  override fun rerunReport(reportID: String) =
    hashIDorThrow(reportID, ::NotFoundException).let { jobID ->
      if (isDiamondQueryJob(jobID))
        throw ForbiddenException()
      else
        BlastReportService.rerunReport(jobID, request.requireUserID())
    }

  @Override
  override fun getReportData(
    reportID: String,
    fileName: String,
    download: Boolean,
    headers: String?,
    lines: String?,
    contentMaxLength: Long?,
    contentRange: String?,
  ): Response =
    hashIDorThrow(reportID, ::NotFoundException).let { jobID ->
      val range = if (lines != null) {
        if (contentRange != null) {
          throw BadRequestException("cannot specify both a lines query param and a Content-Range header")
        }

        ContentRange(ContentRange.Units.Lines, lines)
      } else if (contentRange != null) {
        ContentRange(contentRange)
      } else {
        null
      }

      if (isDiamondQueryJob(jobID)) {
        if (fileName == DiamondWorkspace.ResultFile) {
          DiamondReportService.downloadReport(jobID, download, headers, range).toResponse()
        } else {
          throw NotFoundException()
        }
      } else {
        if (headers != null)
          throw BadRequestException("headers query param cannot be used for blast reports")

        BlastReportService.downloadReport(jobID, request.requireUserID(), fileName, download, contentMaxLength, range)
          .toResponse()
      }
    }

  private fun isDiamondQueryJob(reportID: HashID): Boolean {
    return BlastDBManager().use { it.getBlastRow(reportID) }
      ?.also { logger.debug("found row: {}") { Json.Mapper.writeValueAsString(it) }  }
      ?.takeIf { it.config is DiamondConfig } != null
  }
}
