package org.veupathdb.service.multiblast.model.blast.trait;

import java.util.Collection;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithTaxIds <T> extends CliOptions
{
  int[] getTaxIds();
  T setTaxIds(int[] taxIds);
  T setTaxIds(Collection<Integer> taxIds);

  int[] getNegativeTaxIds();
  T setNegativeTaxIds(int[] negativeTaxIds);
  T setNegativeTaxIds(Collection<Integer> negativeTaxIds);
}
