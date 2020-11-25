package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.model.blast.CompBasedStats;
import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithCompBasedStats<T> extends CliOptions
{
  CompBasedStats getCompBasedStatisticsType();
  T setCompBasedStatisticsType(CompBasedStats c);
}
