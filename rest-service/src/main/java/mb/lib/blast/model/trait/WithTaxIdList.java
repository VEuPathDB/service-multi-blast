package mb.lib.blast.model.trait;

import java.io.File;

import mb.api.service.cli.CliOptions;

public interface WithTaxIdList <T> extends CliOptions
{
  File getTaxIdListFile();
  T setTaxIDListFile(File f);

  File getNegativeTaxIdListFile();
  T setNegativeTaxIDListFile(File f);
}
