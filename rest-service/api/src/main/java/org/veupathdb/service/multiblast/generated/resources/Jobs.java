package org.veupathdb.service.multiblast.generated.resources;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.veupathdb.service.multiblast.generated.model.*;
import org.veupathdb.service.multiblast.generated.support.ResponseDelegate;

@Path("/jobs")
public interface Jobs {
  @GET
  @Produces("application/json")
  GetJobsResponse getJobs();

  @POST
  @Produces("application/json")
  @Consumes("application/json")
  PostJobsResponse postJobs(IOJsonJobRequest entity);

  @POST
  @Produces("application/json")
  @Consumes("multipart/form-data")
  PostJobsResponse postJobs(IOMultipartJobRequest entity);

  @GET
  @Path("/{job-id}")
  @Produces("application/json")
  GetJobsByJobIdResponse getJobsByJobId(@PathParam("job-id") String jobId);

  @GET
  @Path("/{job-id}/query")
  @Produces({
      "application/json",
      "text/plain"
  })
  GetJobsQueryByJobIdResponse getJobsQueryByJobId(@PathParam("job-id") String jobId,
      @QueryParam("download") @DefaultValue("false") boolean download);

  @GET
  @Path("/{job-id}/report")
  @Produces({
      "application/json",
      "application/zip"
  })
  Response getJobsReportByJobId(
    @PathParam("job-id") String jobId,
    @QueryParam("format") String format,
    @QueryParam("zip") @DefaultValue("true") boolean zip,
    @QueryParam("inline") @DefaultValue("false") boolean inline,
    @QueryParam("fields") List<IOBlastReportField> fields
  );

  class GetJobsResponse extends ResponseDelegate {
    private GetJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    public static GetJobsResponse respond200WithApplicationJson(List<IOShortJobResponse> entity) {
      Response.ResponseBuilder                responseBuilder = Response.status(200).header("Content-Type", "application/json");
      GenericEntity<List<IOShortJobResponse>> wrappedEntity   = new GenericEntity<List<IOShortJobResponse>>(entity){};
      responseBuilder.entity(wrappedEntity);
      return new GetJobsResponse(responseBuilder.build(), wrappedEntity);
    }
  }

  class PostJobsResponse extends ResponseDelegate {
    private PostJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    public static PostJobsResponse respond200WithApplicationJson(IOJobPostResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }
  }

  class GetJobsByJobIdResponse extends ResponseDelegate {
    private GetJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    public static GetJobsByJobIdResponse respond200WithApplicationJson(IOLongJobResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsByJobIdResponse(responseBuilder.build(), entity);
    }
  }

  class GetJobsQueryByJobIdResponse extends ResponseDelegate {
    private GetJobsQueryByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    public static HeadersFor200 headersFor200() {
      return new HeadersFor200();
    }

    public static GetJobsQueryByJobIdResponse respond200WithTextPlain(Object entity,
        HeadersFor200 headers) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "text/plain");
      responseBuilder.entity(entity);
      headers.toResponseBuilder(responseBuilder);
      return new GetJobsQueryByJobIdResponse(responseBuilder.build(), entity);
    }

    public static class HeadersFor200 extends HeaderBuilderBase {
      private HeadersFor200() {
      }

      public HeadersFor200 withContentDisposition(final String p) {
        headerMap.put("Content-Disposition", String.valueOf(p));;
        return this;
      }
    }
  }
}
