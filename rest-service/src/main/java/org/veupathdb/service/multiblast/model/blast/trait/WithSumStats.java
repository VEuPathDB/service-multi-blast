package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithSumStats<T> extends CliOptions
{
  Boolean isSumStatisticsEnabled();
  T enableSumStatistics(Boolean b);
}
