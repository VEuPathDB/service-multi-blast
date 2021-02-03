package org.veupathdb.service.multiblast.controller;

import org.veupathdb.service.multiblast.generated.resources.Meta;
import org.veupathdb.service.multiblast.service.http.MetaService;

public class MetaController implements Meta
{
  @Override
  public GetMetaResponse getMeta(String site) {
    return GetMetaResponse.respond200WithApplicationJson(MetaService.fileTree());
  }
}
