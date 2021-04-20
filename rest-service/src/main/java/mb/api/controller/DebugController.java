package mb.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import mb.api.service.JobCleanup;


@Path("debug")
public class DebugController
{
  @GET
  @Path("prune")
  public void prune() throws Exception {
    new JobCleanup().runChecked();
  }
}
