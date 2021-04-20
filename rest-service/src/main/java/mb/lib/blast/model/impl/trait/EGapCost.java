package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithGapCosts;
import mb.api.service.cli.CliBuilder;

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
