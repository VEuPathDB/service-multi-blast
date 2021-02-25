package org.veupathdb.service.multiblast.generated.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/meta")
public interface Meta {
  @GET
  @Produces("application/json")
  Response getMeta(@QueryParam("site") String site);
}
