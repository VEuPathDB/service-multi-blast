package org.veupathdb.service.multiblast.generated.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.veupathdb.service.multiblast.generated.model.IOGuestLink;

@Path("/link-guest")
public interface LinkGuest
{
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void convGuestToUser(IOGuestLink link);
}
