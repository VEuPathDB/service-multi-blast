package mb.api.controller.resources;

import java.io.InputStream;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import mb.api.model.IOJsonJobRequest;
import mb.lib.http.MimeType;
import org.glassfish.jersey.media.multipart.FormDataParam;

@Path(Paths.Jobs)
public interface Jobs
{
  String JobIDKey   = "job-id";
  String JobIDParam = "{" + JobIDKey + "}";
  String JobIDPath  = "/" + JobIDParam;
  String QueryPath  = JobIDPath + "/query";

  String ReportsPath   = JobIDPath + "/reports";
  String ReportIDKey   = "report-id";
  String ReportIDParam = "{" + ReportIDKey + "}";
  String ReportPath    = ReportsPath + "/" + ReportIDParam;

  String ReportDataPath = ReportPath + "/report";

  @GET
  @Produces(MimeType.ApplicationJSON)
  Response getJobs();

  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes(MimeType.ApplicationJSON)
  Response postJob(IOJsonJobRequest entity);

  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes("multipart/form-data")
  Response postJob(
    @FormDataParam("query") InputStream upload,
    @FormDataParam("properties") InputStream config
  );

  @GET
  @Path(JobIDPath)
  @Produces(MimeType.ApplicationJSON)
  Response getJob(@PathParam(JobIDKey) String jobId);

  @GET
  @Path(QueryPath)
  @Produces({MimeType.ApplicationJSON, MimeType.TextPlain})
  Response getQuery(
    @PathParam(JobIDKey) String jobId,
    @QueryParam("download") @DefaultValue("false") boolean download
  );
}
