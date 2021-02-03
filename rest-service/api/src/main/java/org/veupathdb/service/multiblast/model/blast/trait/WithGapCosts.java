package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithGapCosts<T> extends CliOptions
{
  Integer getGapCostOpen();
  T setGapCostOpen(Integer cost);

  Integer getGapCostExtend();
  T setGapCostExtend(Integer cost);
}
