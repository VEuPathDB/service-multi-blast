package org.veupathdb.service.demo.generated.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import org.veupathdb.service.demo.generated.model.BadRequestError;
import org.veupathdb.service.demo.generated.model.JobsPostMultipartFormData;
import org.veupathdb.service.demo.generated.model.QueryJobDetails;
import org.veupathdb.service.demo.generated.model.QueryJobListEntry;
import org.veupathdb.service.demo.generated.model.ServerError;
import org.veupathdb.service.demo.generated.model.UnauthorizedError;
import org.veupathdb.service.demo.generated.model.UnprocessableEntityError;
import org.veupathdb.service.demo.generated.support.ResponseDelegate;

@Path("/jobs")
public interface Jobs {
  @GET
  @Produces("application/json")
  GetJobsResponse getJobs();

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

    public static PostJobsResponse respond200WithApplicationJson(QueryJobDetails entity) {
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
  }
}
