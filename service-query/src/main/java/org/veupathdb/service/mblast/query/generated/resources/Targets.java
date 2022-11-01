package org.veupathdb.service.mblast.query.generated.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.BlastTargetIndex;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/targets")
public interface Targets {
  @GET
  @Produces("application/json")
  GetTargetsResponse getTargets();

  class GetTargetsResponse extends ResponseDelegate {
    private GetTargetsResponse(Response response, Object entity) {
      super(response, entity);
    }

    private GetTargetsResponse(Response response) {
      super(response);
    }

    public static GetTargetsResponse respond200WithApplicationJson(BlastTargetIndex entity) {
      Response.ResponseBuilder responseBuilder = Response.status(200).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new GetTargetsResponse(responseBuilder.build(), entity);
    }
  }
}
