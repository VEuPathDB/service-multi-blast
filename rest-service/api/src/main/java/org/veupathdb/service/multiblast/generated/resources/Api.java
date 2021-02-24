package org.veupathdb.service.multiblast.generated.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.veupathdb.service.multiblast.generated.support.ResponseDelegate;

@Path("/api")
public interface Api {
  @GET
  @Produces("text/html")
  GetApiResponse getApi();

  class GetApiResponse extends ResponseDelegate {
    private GetApiResponse(Response response, Object entity) {
      super(response, entity);
    }
  }
}
