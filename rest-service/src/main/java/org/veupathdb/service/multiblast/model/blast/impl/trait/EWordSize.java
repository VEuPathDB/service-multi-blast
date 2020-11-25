package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithWordSize;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EWordSize implements WithWordSize<Void>
{
  public Integer wordSize;

  @Override
  public Integer getWordSize() {
    return wordSize;
  }

  @Override
  public Void setWordSize(Integer size) {
    wordSize = size;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.WordSize, wordSize);
  }
}
