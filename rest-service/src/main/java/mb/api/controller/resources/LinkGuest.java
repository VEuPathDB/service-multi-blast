package mb.api.controller.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import mb.api.model.IOGuestLink;

@Path("/link-guest")
public interface LinkGuest
{
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  void convGuestToUser(IOGuestLink link);
}
