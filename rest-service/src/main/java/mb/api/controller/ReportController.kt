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
  override fun getAllReports(jobID: String?): List<ReportResponse> =
    if (jobID != null)
      ReportService.listReports(HashID.parseOrThrow(jobID, ::NotFoundException), request.requireUserID())
    else
      ReportService.listAllReports(request.requireUserID())

  override fun getReport(reportID: String) =
    ReportService.getReport(HashID.parseOrThrow(reportID, ::NotFoundException), request.requireUserID())

  override fun newReport(req: ReportRequest) =
    ReportService.submitReport(req, request.requireUserID())

  override fun rerunReport(reportID: String) =
    ReportService.rerunReport(HashID.parseOrThrow(reportID, ::NotFoundException), request.requireUserID())

  @Override
  override fun getReportData(reportID: String, fileName: String, download: Boolean, contentMaxLength: Long?) =
    ReportService.downloadReport(
      HashID.parseOrThrow(reportID, ::NotFoundException),
      request.requireUserID(),
      fileName,
      download,
      contentMaxLength
    ).toResponse()
}
