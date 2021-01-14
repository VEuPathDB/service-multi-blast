package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithTaxIdList;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ETaxIdList implements WithTaxIdList<Void>
{
  private File taxIdList;
  private File negativeTaxIdList;

  @Override
  public File getTaxIdListFile() {
    return taxIdList;
  }

  @Override
  public Void setTaxIDListFile(File f) {
    taxIdList = f;
    return null;
  }

  @Override
  public File getNegativeTaxIdListFile() {
    return negativeTaxIdList;
  }

  @Override
  public Void setNegativeTaxIDListFile(File f) {
    negativeTaxIdList = f;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.TaxonomyIDListFile, taxIdList)
      .appendNonNull(ToolOption.NegativeTaxonomyIDs, negativeTaxIdList);
  }
}
