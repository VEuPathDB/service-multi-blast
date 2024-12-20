package org.veupathdb.service.mblast.query.generated.resources;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.BadRequestError;
import org.veupathdb.service.mblast.query.generated.model.JobCreateResponse;
import org.veupathdb.service.mblast.query.generated.model.JobsPostMultipartFormData;
import org.veupathdb.service.mblast.query.generated.model.QueryJobListEntry;
import org.veupathdb.service.mblast.query.generated.model.ServerError;
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.query.generated.model.UnprocessableEntityError;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/jobs")
public interface Jobs {
  @GET
  @Produces("application/json")
  GetJobsResponse getJobs(@QueryParam("site") String site);

  @POST
  @Produces("application/json")
  @Consumes("multipart/form-data")
  PostJobsResponse postJobs(JobsPostMultipartFormData entity);

  class GetJobsResponse extends ResponseDelegate {
    private GetJobsResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetJobsResponse(Response response) {
      super(response);
    }

    public static GetJobsResponse respond200WithApplicationJson(List<QueryJobListEntry> entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      GenericEntity<List<QueryJobListEntry>> wrappedEntity = new GenericEntity<List<QueryJobListEntry>>(entity){};
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

    public static PostJobsResponse respond200WithApplicationJson(JobCreateResponse entity) {
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

    public static PostJobsResponse respond409WithApplicationJson(String entity) {
      Response.ResponseBuilder responseBuilder = Response.status(409).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }

    public static PostJobsResponse respond422WithApplicationJson(UnprocessableEntityError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(422).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostJobsResponse(responseBuilder.build(), entity);
    }
  }
}
