package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.*;
import org.veupathdb.service.mblast.report.generated.support.PATCH;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}")
public interface JobsJobId {
  @DELETE
  @Produces("application/json")
  DeleteJobsByJobIdResponse deleteJobsByJobId(@PathParam("job-id") String jobId);

  @GET
  @Produces("application/json")
  GetJobsByJobIdResponse getJobsByJobId(@PathParam("job-id") String jobId,
      @QueryParam("save_job") @DefaultValue("true") Boolean saveJob);

  @POST
  @Produces("application/json")
  PostJobsByJobIdResponse postJobsByJobId(@PathParam("job-id") String jobId);

  @PATCH
  @Produces("application/json")
  @Consumes("application/json")
  PatchJobsByJobIdResponse patchJobsByJobId(@PathParam("job-id") String jobId,
      ReportJobPatchRequest entity);

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

  class PatchJobsByJobIdResponse extends ResponseDelegate {
    private PatchJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PatchJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static PatchJobsByJobIdResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new PatchJobsByJobIdResponse(responseBuilder.build());
    }

    public static PatchJobsByJobIdResponse respond400WithApplicationJson(BadRequestError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PatchJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PatchJobsByJobIdResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PatchJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PatchJobsByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PatchJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PatchJobsByJobIdResponse respond422WithApplicationJson(
        UnprocessableEntityError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(422).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PatchJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static PatchJobsByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PatchJobsByJobIdResponse(responseBuilder.build(), entity);
    }
  }

  class DeleteJobsByJobIdResponse extends ResponseDelegate {
    private DeleteJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private DeleteJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static DeleteJobsByJobIdResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new DeleteJobsByJobIdResponse(responseBuilder.build());
    }

    public static DeleteJobsByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static DeleteJobsByJobIdResponse respond404WithApplicationJson(NotFoundError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(404).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteJobsByJobIdResponse(responseBuilder.build(), entity);
    }

    public static DeleteJobsByJobIdResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteJobsByJobIdResponse(responseBuilder.build(), entity);
    }
  }

  class PostJobsByJobIdResponse extends ResponseDelegate {
    private PostJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static PostJobsByJobIdResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new PostJobsByJobIdResponse(responseBuilder.build());
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
