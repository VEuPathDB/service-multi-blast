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
  /**
   * List User Jobs
   */
  @GET
  @Produces(MimeType.ApplicationJSON)
  Response getJobs();

  /**
   * Create New Job (JSON Only)
   *
   * @param entity JSON job configuration
   */
  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes(MimeType.ApplicationJSON)
  Response postJob(IOJsonJobRequest entity);

  /**
   * Create New Job (JSON + File Upload)
   *
   * @param upload Query file
   * @param config JSON job configuration
   */
  @POST
  @Produces(MimeType.ApplicationJSON)
  @Consumes("multipart/form-data")
  Response postJob(
    @FormDataParam("query")      InputStream upload,
    @FormDataParam("properties") InputStream config
  );

  /**
   * Get Job Details
   *
   * @param jobId ID of the job to look up.
   */
  @GET
  @Path(Paths.JobByID)
  @Produces(MimeType.ApplicationJSON)
  Response getJob(@PathParam(Vars.JobID) String jobId);

  /**
   * Rerun Job
   *
   * @param jobID ID of the job to re-run.
   */
  @POST
  @Path(Paths.JobByID)
  @Produces(MimeType.ApplicationJSON)
  void rerunJob(@PathParam(Vars.JobID) String jobID);

  /**
   * Get Job Query
   *
   * @param jobId    ID of the job whose query should be returned.
   * @param download Whether download headers should be sent with the response.
   */
  @GET
  @Path(Paths.JobQuery)
  @Produces({MimeType.ApplicationJSON, MimeType.TextPlain})
  Response getQuery(
    @PathParam(Vars.JobID) String jobId,
    @QueryParam("download") @DefaultValue("false") boolean download
  );

}
