package org.veupathdb.service.mblast.query.generated.resources;

import jakarta.ws.rs.core.StreamingOutput;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.query.generated.model.NotFoundError;
import org.veupathdb.service.mblast.query.generated.model.ServerError;
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/stderr")
public interface JobsJobIdStderr {
  @GET
  @Produces({
      "application/json",
      "text/plain"
  })
  GetJobsStderrByJobIdResponse getJobsStderrByJobId(@PathParam("job-id") String jobId,
      @QueryParam("download") @DefaultValue("false") Boolean download);

  class GetJobsStderrByJobIdResponse extends ResponseDelegate {
    private GetJobsStderrByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsStderrByJobIdResponse(Response response) {
      super(response);
    }

    public static HeadersFor200 headersFor200() {
      return new HeadersFor200();
    }

    public static GetJobsStderrByJobIdResponse respond200WithTextPlain(StreamingOutput entity,
        HeadersFor200 headers) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "text/plain");
      responseBuilder.entity(entity);
      headers.toResponseBuilder(responseBuilder);
      return new GetJobsStderrByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsStderrByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsStderrByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsStderrByJobIdResponse respond403WithApplicationJson(
        ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsStderrByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsStderrByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsStderrByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsStderrByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsStderrByJobIdResponse(responseBuilder.build(), entity);
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
