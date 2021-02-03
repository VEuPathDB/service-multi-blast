package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithWordSize<T> extends CliOptions
{
  Integer getWordSize();
  T setWordSize(Integer size);
}
