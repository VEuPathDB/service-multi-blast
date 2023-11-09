package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.JobBulkStatusResponse;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

import java.util.List;

@Path("/statuses")
public interface Statuses {
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  PostStatusesResponse postStatuses(List<String> entity);

  class PostStatusesResponse extends ResponseDelegate {
    private PostStatusesResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostStatusesResponse(Response response) {
      super(response);
    }

    public static PostStatusesResponse respond200WithApplicationJson(JobBulkStatusResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostStatusesResponse(responseBuilder.build(), entity);
    }

    public static PostStatusesResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostStatusesResponse(responseBuilder.build(), entity);
    }
  }
}
