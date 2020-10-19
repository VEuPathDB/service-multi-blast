package org.veupathdb.service.demo.generated.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import org.veupathdb.service.demo.generated.model.MetaResponse;
import org.veupathdb.service.demo.generated.model.ServerError;
import org.veupathdb.service.demo.generated.model.UnauthorizedError;
import org.veupathdb.service.demo.generated.support.ResponseDelegate;

@Path("/meta")
public interface Meta {
  @GET
  @Produces("application/json")
  GetMetaResponse getMeta(@QueryParam("site") String site);

  class GetMetaResponse extends ResponseDelegate {
    private GetMetaResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetMetaResponse(Response response) {
      super(response);
    }

    public static GetMetaResponse respond200WithApplicationJson(MetaResponse entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetMetaResponse(responseBuilder.build(), entity);
    }

    public static GetMetaResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetMetaResponse(responseBuilder.build(), entity);
    }

    public static GetMetaResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetMetaResponse(responseBuilder.build(), entity);
    }
  }
}
