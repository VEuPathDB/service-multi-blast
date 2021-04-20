package mb.lib.blast.model.trait;

import java.io.File;

import mb.api.service.cli.CliOptions;

public interface WithGenInfoIdList<T> extends CliOptions
{
  File getGenInfoIdListFile();
  T setGenInfoIdListFile(File f);

  File getNegativeGenInfoIdListFile();
  T setNegativeGenInfoIdListFile(File f);
}
