package mb.api.controller

import jakarta.ws.rs.BadRequestException
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

    val guest = UserProvider.getUsersById(listOf(link.guestID)).values
      .firstOrNull()
      .let { it ?: throw BadRequestException() }
      .let { if (!it.isGuest) throw ForbiddenException() else it }

    JobDBManager().use {
      try {
        it.updateJobOwner(guest.userId, user.userId)
      } catch (e: Exception) {
        throw InternalServerErrorException(e)
      }
    }
  }
}
