package mb.lib.blast.model.trait;

import java.io.File;

import mb.api.service.cli.CliOptions;

public interface WithIdenticalProteinGroupList<T> extends CliOptions
{
  File getIdenticalProteinGroupListFile();
  T setIdenticalProteinGroupListFile(File ipgList);

  File getNegativeIdenticalProteinGroupListFile();
  T setNegativeIdenticalProteinGroupListFile(File negIpgList);
}
