package org.veupathdb.service.demo.generated.resources;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.veupathdb.service.demo.generated.model.JobBulkStatusResponse;
import org.veupathdb.service.demo.generated.model.ServerError;
import org.veupathdb.service.demo.generated.support.ResponseDelegate;

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
