package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.core.StreamingOutput;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.report.generated.model.NotFoundError;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}/stderr")
public interface JobsJobIdStderr {
  @GET
  @Produces({
      "application/json",
      "text/plain"
  })
  GetJobsStderrByJobIdResponse getJobsStderrByJobId(@PathParam("job-id") String jobId);

  class GetJobsStderrByJobIdResponse extends ResponseDelegate {
    private GetJobsStderrByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsStderrByJobIdResponse(Response response) {
      super(response);
    }

    public static GetJobsStderrByJobIdResponse respond200WithTextPlain(StreamingOutput entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "text/plain");
      responseBuilder.entity(entity);
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
  }
}
