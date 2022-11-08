package org.veupathdb.service.mblast.report.generated.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.report.generated.model.BadRequestError;
import org.veupathdb.service.mblast.report.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.report.generated.model.GuestJobTransferRequest;
import org.veupathdb.service.mblast.report.generated.model.ServerError;
import org.veupathdb.service.mblast.report.generated.model.UnauthorizedError;
import org.veupathdb.service.mblast.report.generated.support.ResponseDelegate;

@Path("/link-guest")
public interface LinkGuest {
  @POST
  @Produces("application/json")
  @Consumes("application/json")
  PostLinkGuestResponse postLinkGuest(GuestJobTransferRequest entity);

  class PostLinkGuestResponse extends ResponseDelegate {
    private PostLinkGuestResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostLinkGuestResponse(Response response) {
      super(response);
    }

    public static PostLinkGuestResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new PostLinkGuestResponse(responseBuilder.build());
    }

    public static PostLinkGuestResponse respond400WithApplicationJson(BadRequestError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostLinkGuestResponse(responseBuilder.build(), entity);
    }

    public static PostLinkGuestResponse respond401WithApplicationJson(UnauthorizedError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(401).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostLinkGuestResponse(responseBuilder.build(), entity);
    }

    public static PostLinkGuestResponse respond403WithApplicationJson(ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostLinkGuestResponse(responseBuilder.build(), entity);
    }

    public static PostLinkGuestResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostLinkGuestResponse(responseBuilder.build(), entity);
    }
  }
}
