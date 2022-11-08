package org.veupathdb.service.mblast.report.controller

import jakarta.ws.rs.BadRequestException
import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.core.Context
import org.glassfish.jersey.server.ContainerRequest
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.GuestJobTransferRequest
import org.veupathdb.service.mblast.report.generated.resources.LinkGuest

@Authenticated(allowGuests = false)
class GuestLinkController(@Context request: ContainerRequest) : ControllerBase(request), LinkGuest {
  override fun postLinkGuest(entity: GuestJobTransferRequest?): LinkGuest.PostLinkGuestResponse {
    if (entity == null)
      throw BadRequestException("request body was empty")
    if (entity.guestID == null)
      throw BadRequestException("guestID was null")

    when (ReportDB.userIsGuest(entity.guestID)) {
      null  -> throw ForbiddenException("target user does not exist")
      false -> throw ForbiddenException("target user is not a guest")
      else  -> ReportDB.updateUserLinksOwner(entity.guestID, userID)
    }

    return LinkGuest.PostLinkGuestResponse.respond204()
  }
}