package org.veupathdb.service.mblast.query.generated.resources;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.query.generated.model.NotFoundError;
import org.veupathdb.service.mblast.query.generated.model.QueryJobDetails;
import org.veupathdb.service.mblast.query.generated.model.ServerError;
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/jobs/{job-id}")
public interface JobsJobId {
  @GET
  @Produces("application/json")
  GetJobsByJobIdResponse getJobsByJobId(@PathParam("job-id") String jobId,
      @QueryParam("saveJob") @DefaultValue("true") boolean saveJob);

  @POST
  @Produces("application/json")
  PostJobsByJobIdResponse postJobsByJobId(@PathParam("job-id") String jobId);

  @DELETE
  @Produces("application/json")
  DeleteJobsByJobIdResponse deleteJobsByJobId(@PathParam("job-id") String jobId);

  class GetJobsByJobIdResponse extends ResponseDelegate {
    private GetJobsByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsByJobIdResponse(Response response) {
      super(response);
    }

    public static GetJobsByJobIdResponse respond200WithApplicationJson(QueryJobDetails entity) {
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
