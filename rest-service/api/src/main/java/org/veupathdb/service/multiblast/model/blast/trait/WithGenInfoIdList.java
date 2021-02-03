package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithGenInfoIdList<T> extends CliOptions
{
  File getGenInfoIdListFile();
  T setGenInfoIdListFile(File f);

  File getNegativeGenInfoIdListFile();
  T setNegativeGenInfoIdListFile(File f);
}
