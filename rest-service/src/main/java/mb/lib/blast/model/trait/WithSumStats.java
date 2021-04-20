package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithSumStats<T> extends CliOptions
{
  Boolean isSumStatisticsEnabled();
  T enableSumStatistics(Boolean b);
}
