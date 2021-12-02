package mb.api.controller.resources

import mb.api.model.IOGuestLink
import javax.ws.rs.Consumes
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType

@Path("/link-guest")
interface LinkGuest
{
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  fun convGuestToUser(link: IOGuestLink)
}
