package mb.lib.blast.model.trait;

import java.io.File;

import mb.api.service.cli.CliOptions;

public interface WithSequenceIdList <T> extends CliOptions
{
  File getSequenceIdListFile();
  T setSequenceIDListFile(File f);

  File getNegativeSequenceIdListFile();
  T setNegativeSequenceIDListFile(File f);
}
