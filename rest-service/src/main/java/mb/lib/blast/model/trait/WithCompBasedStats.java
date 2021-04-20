package mb.lib.blast.model.trait;

import mb.lib.blast.model.CompBasedStats;
import mb.api.service.cli.CliOptions;

public interface WithCompBasedStats<T> extends CliOptions
{
  CompBasedStats getCompBasedStatisticsType();
  T setCompBasedStatisticsType(CompBasedStats c);
}
