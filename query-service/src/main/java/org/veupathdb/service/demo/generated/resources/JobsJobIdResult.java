package org.veupathdb.service.demo.generated.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.veupathdb.service.demo.generated.model.ForbiddenError;
import org.veupathdb.service.demo.generated.model.NotFoundError;
import org.veupathdb.service.demo.generated.model.ServerError;
import org.veupathdb.service.demo.generated.model.UnauthorizedError;
import org.veupathdb.service.demo.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/result")
public interface JobsJobIdResult {
  @GET
  @Produces({
      "application/json",
      "text/plain"
  })
  GetJobsResultByJobIdResponse getJobsResultByJobId(@PathParam("job-id") String jobId,
      @QueryParam("download") @DefaultValue("false") boolean download);

  class GetJobsResultByJobIdResponse extends ResponseDelegate {
    private GetJobsResultByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsResultByJobIdResponse(Response response) {
      super(response);
    }

    public static HeadersFor200 headersFor200() {
      return new HeadersFor200();
    }

    public static GetJobsResultByJobIdResponse respond200WithTextPlain(String entity,
        HeadersFor200 headers) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "text/plain");
      responseBuilder.entity(entity);
      headers.toResponseBuilder(responseBuilder);
      return new GetJobsResultByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsResultByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResultByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsResultByJobIdResponse respond403WithApplicationJson(
        ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResultByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsResultByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResultByJobIdResponse(responseBuilder.build(), entity);
    }

    public static GetJobsResultByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetJobsResultByJobIdResponse(responseBuilder.build(), entity);
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
