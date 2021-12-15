package mb.api.controller.resources

import mb.api.model.io.Headers
import mb.api.model.reports.ReportRequest
import mb.api.model.reports.ReportResponse
import mb.lib.http.MimeType
import javax.ws.rs.*
import javax.ws.rs.core.Response

@Path(Paths.Reports)
interface Reports
{
  /**
   * GET /reports
   */
  @GET
  @Produces(MimeType.ApplicationJSON)
  fun getAllReports(@QueryParam(Vars.JobID) jobID: String?): List<ReportResponse>

  /**
   * POST /reports
   */
  @POST
  @Produces(MimeType.ApplicationJSON)
  fun newReport(req: ReportRequest): ReportResponse

  /**
   * GET /reports/{report-id}
   */
  @GET
  @Path(Paths.ReportByID)
  @Produces(MimeType.ApplicationJSON)
  fun getReport(@PathParam(Vars.ReportID) reportID: String): ReportResponse

  /**
   * POST /reports/{report-id}
   */
  @POST
  @Path(Paths.ReportByID)
  @Produces(MimeType.ApplicationJSON)
  fun rerunReport(@PathParam(Vars.ReportID) reportID: String): ReportResponse

  /**
   * GET /reports/{report-id}/files/{filename}
   */
  @GET
  @Path(Paths.ReportData)
  @Produces(value = [
    MimeType.ApplicationBinary,
    MimeType.ApplicationJSON,
    MimeType.ApplicationXML,
    MimeType.ApplicationZip,
    MimeType.TextPlain
  ])
  fun getReportData(
    @PathParam(Vars.ReportID) reportID: String,
    @PathParam(Vars.FileName) fileName: String,
    @QueryParam("download") @DefaultValue("true") download: Boolean,
    @HeaderParam(Headers.ContentMaxLength) contentMaxLength: Long?,
  ): Response
}
