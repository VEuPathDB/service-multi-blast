package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithCullingLimit<T> extends CliOptions
{
  Integer getCullingLimit();
  T setCullingLimit(Integer v);
}
