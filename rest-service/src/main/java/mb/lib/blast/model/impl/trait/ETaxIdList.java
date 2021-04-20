package mb.lib.blast.model.impl.trait;

import java.io.File;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithTaxIdList;
import mb.api.service.cli.CliBuilder;

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
      .appendNonNull(ToolOption.NegativeTaxonomyIDListFile, negativeTaxIdList);
  }
}
