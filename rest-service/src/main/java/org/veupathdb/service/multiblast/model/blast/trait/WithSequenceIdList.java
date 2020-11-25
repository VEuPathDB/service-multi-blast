package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithSequenceIdList <T> extends CliOptions
{
  File getSequenceIdListFile();
  T setSequenceIdListFile(File f);

  File getNegativeSequenceIdListFile();
  T setNegativeSequenceIdListFile(File f);
}
