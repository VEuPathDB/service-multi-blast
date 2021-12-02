package mb.api.controller

import mb.lib.clean.JobCleanup
import javax.ws.rs.GET
import javax.ws.rs.Path


@Path("debug")
class DebugController {
  @GET
  @Path("prune")
  fun prune() = JobCleanup.run()
}
