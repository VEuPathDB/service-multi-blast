package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithSumStats;
import mb.api.service.cli.CliBuilder;

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
