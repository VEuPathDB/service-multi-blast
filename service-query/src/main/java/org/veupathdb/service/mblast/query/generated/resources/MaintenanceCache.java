package org.veupathdb.service.mblast.query.generated.resources;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.ServerError;
import org.veupathdb.service.mblast.query.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/maintenance/cache")
public interface MaintenanceCache {
  @DELETE
  @Produces("application/json")
  DeleteMaintenanceCacheResponse deleteMaintenanceCache(
      @HeaderParam("Admin-Auth") String adminAuth);

  @DELETE
  @Path("/{job-id}")
  @Produces("application/json")
  DeleteMaintenanceCacheByJobIdResponse deleteMaintenanceCacheByJobId(
      @PathParam("job-id") String jobId, @HeaderParam("Admin-Auth") String adminAuth);

  class DeleteMaintenanceCacheResponse extends ResponseDelegate {
    private DeleteMaintenanceCacheResponse(Response response, Object entity) {
      super(response, entity);
    }

    private DeleteMaintenanceCacheResponse(Response response) {
      super(response);
    }

    public static DeleteMaintenanceCacheResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new DeleteMaintenanceCacheResponse(responseBuilder.build());
    }

    public static DeleteMaintenanceCacheResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteMaintenanceCacheResponse(responseBuilder.build(), entity);
    }

    public static DeleteMaintenanceCacheResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteMaintenanceCacheResponse(responseBuilder.build(), entity);
    }
  }

  class DeleteMaintenanceCacheByJobIdResponse extends ResponseDelegate {
    private DeleteMaintenanceCacheByJobIdResponse(Response response, Object entity) {
      super(response, entity);
    }

    private DeleteMaintenanceCacheByJobIdResponse(Response response) {
      super(response);
    }

    public static DeleteMaintenanceCacheByJobIdResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new DeleteMaintenanceCacheByJobIdResponse(responseBuilder.build());
    }

    public static DeleteMaintenanceCacheByJobIdResponse respond401WithApplicationJson(
        UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteMaintenanceCacheByJobIdResponse(responseBuilder.build(), entity);
    }

    public static DeleteMaintenanceCacheByJobIdResponse respond500WithApplicationJson(
        ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new DeleteMaintenanceCacheByJobIdResponse(responseBuilder.build(), entity);
    }
  }
}
