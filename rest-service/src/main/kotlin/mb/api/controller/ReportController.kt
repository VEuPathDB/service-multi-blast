package mb.api.controller

import mb.api.controller.resources.Reports
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.api.service.http.report.ReportService
import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest

data class ReportController(@Context private val request: ContainerRequest): Reports
{
  override fun getAllReports(jobID: String?): List<ReportResponse> =
    if (jobID != null)
      ReportService.listReports(hashIDorThrow(jobID, ::NotFoundException), request.requireUserID())
    else
      ReportService.listAllReports(request.requireUserID())

  override fun getReport(reportID: String) =
    ReportService.getReport(hashIDorThrow(reportID, ::NotFoundException), request.requireUserID())

  override fun newReport(req: ReportRequest) =
    ReportService.submitReport(req, request.requireUserID())

  override fun rerunReport(reportID: String) =
    ReportService.rerunReport(hashIDorThrow(reportID, ::NotFoundException), request.requireUserID())

  @Override
  override fun getReportData(reportID: String, fileName: String, download: Boolean, contentMaxLength: Long?) =
    ReportService.downloadReport(
      hashIDorThrow(reportID, ::NotFoundException),
      request.requireUserID(),
      fileName,
      download,
      contentMaxLength
    ).toResponse()
}
