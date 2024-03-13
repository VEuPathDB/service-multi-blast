package mb.api.controller

import mb.api.controller.resources.LinkGuest
import mb.api.model.IOGuestLink
import mb.lib.db.JobDBManager
import org.veupathdb.lib.container.jaxrs.providers.UserProvider
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.InternalServerErrorException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest

@Authenticated
data class GuestLinkController(@Context val req: ContainerRequest): LinkGuest {

  override fun convGuestToUser(link: IOGuestLink) {
    val user = UserProvider.lookupUser(req).orElseThrow(::InternalServerErrorException)

    JobDBManager().use {
      try {
        if (it.userIsGuest(link.guestID)) {
          it.updateJobOwner(link.guestID, user.userId)
        } else {
          throw ForbiddenException()
        }
      } catch (e: Exception) {
        throw InternalServerErrorException(e)
      }
    }
  }
}
