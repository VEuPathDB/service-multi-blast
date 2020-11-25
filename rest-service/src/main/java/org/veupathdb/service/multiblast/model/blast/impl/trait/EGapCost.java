package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithGapCosts;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EGapCost implements WithGapCosts<Void>
{
  private Integer gapOpenCost;
  private Integer gapExtendCost;

  @Override
  public Integer getGapCostOpen() {
    return gapOpenCost;
  }

  @Override
  public Void setGapCostOpen(Integer cost) {
    gapOpenCost = cost;
    return null;
  }

  @Override
  public Integer getGapCostExtend() {
    return gapExtendCost;
  }

  @Override
  public Void setGapCostExtend(Integer cost) {
    gapExtendCost = cost;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.GapCostOpen, gapOpenCost)
      .appendNonNull(ToolOption.GapCostExtend, gapExtendCost);
  }
}
