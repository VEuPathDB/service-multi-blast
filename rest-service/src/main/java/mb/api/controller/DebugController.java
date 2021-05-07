package mb.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import mb.lib.clean.JobCleanup;


@Path("debug")
public class DebugController
{
  @GET
  @Path("prune")
  public void prune() throws Exception {
    new JobCleanup().run();
  }
}
