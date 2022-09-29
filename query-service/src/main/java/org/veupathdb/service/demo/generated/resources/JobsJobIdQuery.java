package org.veupathdb.service.demo.generated.resources;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.veupathdb.service.demo.generated.model.NotFoundError;
import org.veupathdb.service.demo.generated.model.ServerError;
import org.veupathdb.service.demo.generated.model.UnauthorizedError;
import org.veupathdb.service.demo.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/query")
public interface JobsJobIdQuery {
  @GET
  @Produces({
      "application/json",
      "text/plain"
  })
  GetJobsQueryByJobIdResponse getJobsQueryByJobId(@PathParam("job-id") String jobId,
      @QueryParam("download") @DefaultValue("false") boolean download);

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

    public static GetJobsQueryByJobIdResponse respond200WithTextPlain(String entity,
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
}
