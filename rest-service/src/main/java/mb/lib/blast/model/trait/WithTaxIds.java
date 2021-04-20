package mb.lib.blast.model.trait;

import java.util.Collection;

import mb.api.service.cli.CliOptions;

public interface WithTaxIds <T> extends CliOptions
{
  int[] getTaxIds();
  T setTaxIDs(int[] taxIds);
  T setTaxIDs(Collection<Integer> taxIds);

  int[] getNegativeTaxIds();
  T setNegativeTaxIds(int[] negativeTaxIds);
  T setNegativeTaxIds(Collection<Integer> negativeTaxIds);
}
