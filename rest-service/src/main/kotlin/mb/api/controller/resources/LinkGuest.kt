package mb.api.controller.resources

import mb.api.model.IOGuestLink
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.core.MediaType

@Path("/link-guest")
interface LinkGuest
{
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  fun convGuestToUser(link: IOGuestLink)
}
