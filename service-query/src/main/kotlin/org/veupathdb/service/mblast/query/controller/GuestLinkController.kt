package org.veupathdb.service.mblast.query.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.query.generated.model.GuestJobTransferRequest
import org.veupathdb.service.mblast.query.generated.resources.LinkGuest
import org.veupathdb.service.mblast.query.sql.JobDBManager

@Authenticated(allowGuests = false)
class GuestLinkController(@Context request: ContainerRequest) : ControllerBase(request), LinkGuest {
  override fun postLinkGuest(entity: GuestJobTransferRequest): LinkGuest.PostLinkGuestResponse {
    if (entity.guestID == null)
      throw BadRequestException()

    when (JobDBManager.userIsGuest(entity.guestID)) {
      null  -> throw ForbiddenException()
      false -> throw ForbiddenException()
      else  -> JobDBManager.updateUserLinksOwner(entity.guestID, userID)
    }

    return LinkGuest.PostLinkGuestResponse.respond204()
  }
}