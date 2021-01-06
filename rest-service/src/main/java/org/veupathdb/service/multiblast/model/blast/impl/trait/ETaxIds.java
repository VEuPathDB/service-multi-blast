package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithTaxIds;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ETaxIds implements WithTaxIds<Void>
{
  private int[] taxIds;
  private int[] negativeTaxIds;

  @Override
  public int[] getTaxIds() {
    return taxIds;
  }

  @Override
  public Void setTaxIds(int[] taxIds) {
    this.taxIds = taxIds;
    return null;
  }

  @Override
  public Void setTaxIds(Collection<Integer> taxIds) {
    this.taxIds = taxIds == null ? null : taxIds.stream().mapToInt(Integer::intValue).toArray();
    return null;
  }

  @Override
  public int[] getNegativeTaxIds() {
    return negativeTaxIds;
  }

  @Override
  public Void setNegativeTaxIds(int[] negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds;
    return null;
  }

  @Override
  public Void setNegativeTaxIds(Collection<Integer> negativeTaxIds) {
    this.negativeTaxIds = negativeTaxIds == null
      ? null
      : negativeTaxIds.stream().mapToInt(Integer::intValue).toArray();
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    if (taxIds != null)
      cli.appendNonNull(ToolOption.TaxonomyIDs, Arrays.stream(taxIds)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(",")));
    if (negativeTaxIds != null)
      cli.appendNonNull(ToolOption.NegativeTaxonomyIDs, Arrays.stream(negativeTaxIds)
        .mapToObj(Integer::toString)
        .collect(Collectors.joining(",")));
  }
}
