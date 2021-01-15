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
  PostJobsResponse postJobs(NewJobPostRequestJSON entity);

  @POST
  @Produces("application/json")
  @Consumes("multipart/form-data")
  PostJobsResponse postJobs(NewJobPostRequestMultipart entity);

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
  GetJobsReportByJobIdResponse getJobsReportByJobId(@PathParam("job-id") String jobId,
      @QueryParam("format") IOBlastFormat format,
      @QueryParam("fields") List<IOBlastReportField> fields);

  class GetJobsResponse extends ResponseDelegate {
    private GetJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsResponse(Response response) {
      super(response);
    }

    public static GetJobsResponse respond200WithApplicationJson(List<ShortJobResponse> entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      GenericEntity<List<ShortJobResponse>> wrappedEntity = new GenericEntity<List<ShortJobResponse>>(entity){};
      responseBuilder.entity(wrappedEntity);
      return new GetJobsResponse(responseBuilder.build(), wrappedEntity);
    }

    public static GetJobsResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResponse(responseBuilder.build(), entity);
    }

    public static GetJobsResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResponse(responseBuilder.build(), entity);
    }
  }

  class PostJobsResponse extends ResponseDelegate {
    private PostJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostJobsResponse(Response response) {
      super(response);
    }

    public static PostJobsResponse respond200WithApplicationJson(NewJobPostResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }

    public static PostJobsResponse respond400WithApplicationJson(BadRequestError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }

    public static PostJobsResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }

    public static PostJobsResponse respond422WithApplicationJson(UnprocessableEntityError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(422).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }

    public static PostJobsResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }
  }

  class GetJobsByJobIdResponse extends ResponseDelegate {
    private GetJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static GetJobsByJobIdResponse respond200WithApplicationJson(LongJobResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsByJobIdResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsByJobIdResponse(responseBuilder.build(), entity);
    }
  }

  class GetJobsQueryByJobIdResponse extends ResponseDelegate {
    private GetJobsQueryByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsQueryByJobIdResponse(Response response) {
      super(response);
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

    public static GetJobsQueryByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsQueryByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsQueryByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsQueryByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsQueryByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
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

  class GetJobsReportByJobIdResponse extends ResponseDelegate {
    private GetJobsReportByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsReportByJobIdResponse(Response response) {
      super(response);
    }

    public static HeadersFor200 headersFor200() {
      return new HeadersFor200();
    }

    public static GetJobsReportByJobIdResponse respond200WithApplicationZip(Object entity,
        HeadersFor200 headers) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/zip");
      responseBuilder.entity(entity);
      headers.toResponseBuilder(responseBuilder);
      return new GetJobsReportByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsReportByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsReportByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsReportByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsReportByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsReportByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsReportByJobIdResponse(responseBuilder.build(), entity);
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
