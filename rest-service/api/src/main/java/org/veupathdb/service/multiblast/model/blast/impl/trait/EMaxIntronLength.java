package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithMaxIntronLength;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EMaxIntronLength implements WithMaxIntronLength<Void>
{
  private Integer maxIntronLength;

  @Override
  public Integer getMaxIntronLength() {
    return maxIntronLength;
  }

  @Override
  public Void setMaxIntronLength(Integer len) {
    maxIntronLength = len;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.MaxIntronLength, maxIntronLength);
  }
}
