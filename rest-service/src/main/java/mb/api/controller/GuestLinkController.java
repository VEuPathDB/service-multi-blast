package mb.api.controller;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

import mb.lib.db.JobDBManager;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated;
import mb.api.model.IOGuestLink;
import mb.api.controller.resources.LinkGuest;

@Authenticated
public class GuestLinkController implements LinkGuest
{
  private final Request req;

  public GuestLinkController(@Context Request req) {
    this.req = req;
  }

  @Override
  public void convGuestToUser(IOGuestLink link) {
    var user = UserProvider.lookupUser(req).orElseThrow(InternalServerErrorException::new);

    try (var db = new JobDBManager()) {
      if (db.userIsGuest(link.getGuestID())) {
        db.updateJobOwner(link.getGuestID(), user.getUserID());
      } else {
        throw new ForbiddenException();
      }
    } catch (Exception e) {
      throw new InternalServerErrorException(e);
    }
  }
}
