package org.veupathdb.service.multiblast.controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.veupathdb.service.multiblast.generated.resources.Meta;
import org.veupathdb.service.multiblast.service.http.MetaService;

public class MetaController implements Meta
{
  @Override
  public Response getMeta(String site) {
    return Response.status(Response.Status.OK)
      .type(MediaType.APPLICATION_JSON_TYPE)
      .entity(MetaService.fileTree())
      .build();
  }
}
