package org.veupathdb.service.mblast.query.generated.resources;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.veupathdb.service.mblast.query.generated.model.BadRequestError;
import org.veupathdb.service.mblast.query.generated.model.ForbiddenError;
import org.veupathdb.service.mblast.query.generated.model.RPCParamsJobExpire;
import org.veupathdb.service.mblast.query.generated.model.ServerError;
import org.veupathdb.service.mblast.query.generated.support.ResponseDelegate;

@Path("/rpc/jobs")
public interface RpcJobs {
  @POST
  @Path("/expire")
  @Produces("application/json")
  @Consumes("application/json")
  PostRpcJobsExpireResponse postRpcJobsExpire(RPCParamsJobExpire entity);

  class PostRpcJobsExpireResponse extends ResponseDelegate {
    private PostRpcJobsExpireResponse(Response response, Object entity) {
      super(response, entity);
    }

    private PostRpcJobsExpireResponse(Response response) {
      super(response);
    }

    public static PostRpcJobsExpireResponse respond204() {
      Response.ResponseBuilder responseBuilder = Response.status(204);
      return new PostRpcJobsExpireResponse(responseBuilder.build());
    }

    public static PostRpcJobsExpireResponse respond400WithApplicationJson(BadRequestError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(400).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostRpcJobsExpireResponse(responseBuilder.build(), entity);
    }

    public static PostRpcJobsExpireResponse respond401() {
      Response.ResponseBuilder responseBuilder = Response.status(401);
      return new PostRpcJobsExpireResponse(responseBuilder.build());
    }

    public static PostRpcJobsExpireResponse respond403WithApplicationJson(ForbiddenError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(403).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostRpcJobsExpireResponse(responseBuilder.build(), entity);
    }

    public static PostRpcJobsExpireResponse respond500WithApplicationJson(ServerError entity) {
      Response.ResponseBuilder responseBuilder = Response.status(500).header("Content-Type", "application/json");
      responseBuilder.entity(entity);
      return new PostRpcJobsExpireResponse(responseBuilder.build(), entity);
    }
  }
}
