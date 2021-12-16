package mb.api.controller

import mb.lib.clean.JobCleanup
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path


@Path("debug")
class DebugController {
  @GET
  @Path("prune")
  fun prune() = JobCleanup.run()
}
