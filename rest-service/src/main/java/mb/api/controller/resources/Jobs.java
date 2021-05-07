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
  @Path(Paths.JobByID)
  @Produces(MimeType.ApplicationJSON)
  Response getJob(@PathParam(Vars.JobID) String jobId);

  @GET
  @Path(Paths.JobQuery)
  @Produces({MimeType.ApplicationJSON, MimeType.TextPlain})
  Response getQuery(
    @PathParam(Vars.JobID) String jobId,
    @QueryParam("download") @DefaultValue("false") boolean download
  );
}
