package mb.api.controller

import jakarta.ws.rs.ForbiddenException
import mb.api.controller.resources.Reports
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.service.http.report.BlastReportService
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Context
import jakarta.ws.rs.core.Response
import mb.api.service.http.report.BlastReportService.convert
import mb.api.service.http.report.DiamondReportService
import mb.api.service.http.wrap
import mb.lib.query.BlastDBManager
import mb.lib.query.model.DiamondConfig
import mb.lib.report.ReportManager
import mb.lib.workspace.DiamondWorkspace
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.lib.hash_id.HashID

@Authenticated(allowGuests = true)
class ReportController(@Context private val request: ContainerRequest): Reports
{
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
  override fun getReportData(reportID: String, fileName: String, download: Boolean, contentMaxLength: Long?): Response =
    hashIDorThrow(reportID, ::NotFoundException).let { jobID ->
      if (isDiamondQueryJob(jobID)) {
        if (fileName == DiamondWorkspace.ResultFile) {
          DiamondReportService.downloadReport(jobID, download).toResponse()
        } else {
          throw NotFoundException()
        }
      } else {
        BlastReportService.downloadReport(jobID, request.requireUserID(), fileName, download, contentMaxLength)
          .toResponse()
      }
    }

  private fun isDiamondQueryJob(reportID: HashID): Boolean {
    return BlastDBManager().use { it.getBlastRow(reportID) }
      ?.takeIf { it.config is DiamondConfig } != null
  }
}
