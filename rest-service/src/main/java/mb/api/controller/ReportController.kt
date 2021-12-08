package mb.api.controller

import mb.api.controller.resources.Reports
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.service.http.report.ReportService
import mb.lib.model.HashID
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import javax.ws.rs.NotFoundException
import javax.ws.rs.core.Context
import javax.ws.rs.core.Request
import javax.ws.rs.core.Response

@Authenticated(allowGuests = true)
data class ReportController(@Context private val request: Request): Reports
{
  private val Log = LogManager.getLogger(ReportController::class.java)

  override fun getAllReports(jobID: String?): List<ReportResponse> {
    Log.trace("#getAllReports(jobID={})", jobID)

    if (jobID != null)
      return ReportService.listReports(HashID.parseOrThrow(jobID, ::NotFoundException), request.requireUserID())

    return ReportService.listAllReports(request.requireUserID())
  }

  override fun getReport(reportID: String): ReportResponse {
    Log.trace("#getReport(reportID={})", reportID)

    return ReportService.getReport(HashID.parseOrThrow(reportID, ::NotFoundException), request.requireUserID())
  }

  override fun newReport(req: ReportRequest): ReportResponse {
    Log.trace("#newReport(req={})", req)

    return ReportService.submitReport(req, request.requireUserID())
  }

  override fun rerunReport(reportID: String): ReportResponse {
    Log.trace("#rerunReport(reportID={})", reportID)

    return ReportService.rerunReport(HashID.parseOrThrow(reportID, ::NotFoundException), request.requireUserID())
  }

  @Override
  override fun getReportData(reportID: String, fileName: String, download: Boolean, contentMaxLength: Long?): Response {
    Log.trace("getReportData(reportID={}, fileName={}, download={}, contentMaxLength={})", reportID, fileName, download, contentMaxLength)

    return ReportService.downloadReport(
      HashID.parseOrThrow(reportID, ::NotFoundException),
      request.requireUserID(),
      fileName,
      download,
      contentMaxLength
    ).toResponse()
  }
}
