package org.veupathdb.service.multiblast.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.veupathdb.service.multiblast.service.JobCleanup;


@Path("debug")
public class DebugController
{
  @GET
  @Path("prune")
  public void prune() throws Exception {
    new JobCleanup().runChecked();
  }
}
