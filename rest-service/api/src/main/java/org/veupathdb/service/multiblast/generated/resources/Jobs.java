package org.veupathdb.service.multiblast.generated.resources;

import java.io.InputStream;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;
import org.veupathdb.service.multiblast.generated.model.IOBlastReportField;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;

@Path("/jobs")
public interface Jobs
{
  @GET
  @Produces("application/json")
  Response getJobs();

  @POST
  @Produces("application/json")
  @Consumes("application/json")
  Response postJobs(IOJsonJobRequest entity);

  @POST
  @Produces("application/json")
  @Consumes("multipart/form-data")
  Response postJobs(
    @FormDataParam("query") InputStream upload,
    @FormDataParam("properties") InputStream config
  );

  @GET
  @Path("/{job-id}")
  @Produces("application/json")
  Response getJobsByJobId(@PathParam("job-id") String jobId);

  @GET
  @Path("/{job-id}/query")
  @Produces({"application/json", "text/plain"})
  Response getJobsQueryByJobId(
    @PathParam("job-id") String jobId,
    @QueryParam("download") @DefaultValue("false") boolean download
  );

  @GET
  @Path("/{job-id}/report")
  @Produces({"application/json", "application/zip"})
  Response getJobsReportByJobId(
    @PathParam("job-id") String jobId,
    @QueryParam("format") String format,
    @QueryParam("zip") @DefaultValue("true") boolean zip,
    @QueryParam("inline") @DefaultValue("false") boolean inline,
    @QueryParam("fields") List<IOBlastReportField> fields
  );

}
