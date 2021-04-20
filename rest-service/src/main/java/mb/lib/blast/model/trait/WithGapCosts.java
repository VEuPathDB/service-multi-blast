package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithGapCosts<T> extends CliOptions
{
  Integer getGapCostOpen();
  T setGapCostOpen(Integer cost);

  Integer getGapCostExtend();
  T setGapCostExtend(Integer cost);
}
