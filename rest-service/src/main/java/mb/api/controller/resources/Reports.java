package mb.api.controller.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import mb.api.model.reports.ReportRequest;
import mb.api.model.reports.ReportResponse;
import mb.lib.http.MimeType;

@Path(Paths.Reports)
public interface Reports
{
  @GET
  @Produces(MimeType.ApplicationJSON)
  List<ReportResponse> getAllReports(@QueryParam(Vars.JobID) String jobID);

  @POST
  @Produces(MimeType.ApplicationJSON)
  ReportResponse newReport(ReportRequest req);

  @GET
  @Path(Paths.ReportByID)
  @Produces(MimeType.ApplicationJSON)
  ReportResponse getReport(@PathParam(Vars.ReportID) String reportID);

  @POST
  @Path(Paths.ReportByID)
  @Produces(MimeType.ApplicationJSON)
  ReportResponse rerunReport(@PathParam(Vars.ReportID) String reportID);

  @GET
  @Path(Paths.ReportData)
  @Produces({
    MimeType.ApplicationBinary,
    MimeType.ApplicationJSON,
    MimeType.ApplicationXML,
    MimeType.ApplicationZip,
    MimeType.TextPlain
  })
  Response getReportData(
    @PathParam(Vars.ReportID) String reportID,
    @PathParam(Vars.FileName) String fileName,
    @QueryParam("download") @DefaultValue("true") boolean download,
    @QueryParam("zip") @DefaultValue("true") boolean zip
  );
}
