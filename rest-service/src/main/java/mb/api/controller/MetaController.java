package mb.api.controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import mb.api.controller.resources.Meta;
import mb.api.service.http.MetaService;

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
