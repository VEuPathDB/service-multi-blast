package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.report.generated.model.NotFoundError;
import org.veupathdb.service.mblast.report.generated.model.ReportJobDetails;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}")
public interface JobsJobId {
  @GET
  @Produces("application/json")
  GetJobsByJobIdResponse getJobsByJobId(@PathParam("job-id") String jobId);

  @POST
  @Produces("application/json")
  PostJobsByJobIdResponse postJobsByJobId(@PathParam("job-id") String jobId);

  class GetJobsByJobIdResponse extends ResponseDelegate {
    private GetJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static GetJobsByJobIdResponse respond200WithApplicationJson(ReportJobDetails entity) {
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

  class PostJobsByJobIdResponse extends ResponseDelegate {
    private PostJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static PostJobsByJobIdResponse respond200WithApplicationJson(ReportJobDetails entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PostJobsByJobIdResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PostJobsByJobIdResponse respond403WithApplicationJson(ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PostJobsByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PostJobsByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsByJobIdResponse(responseBuilder.build(), entity);
    }
  }
}
