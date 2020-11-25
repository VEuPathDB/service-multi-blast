package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithSumStats;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ESumStats implements WithSumStats<Void>
{
  private Boolean sumStats;

  @Override
  public Boolean isSumStatisticsEnabled() {
    return sumStats;
  }

  @Override
  public Void enableSumStatistics(Boolean b) {
    this.sumStats = b;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.SumStats, sumStats);
  }
}
