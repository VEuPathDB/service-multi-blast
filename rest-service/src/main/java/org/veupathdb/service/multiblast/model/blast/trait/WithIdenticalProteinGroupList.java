package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithIdenticalProteinGroupList<T> extends CliOptions
{
  File getIdenticalProteinGroupListFile();
  T setIdenticalProteinGroupListFile(File ipgList);

  File getNegativeIdenticalProteinGroupListFile();
  T setNegativeIdenticalProteinGroupListFile(File negIpgList);
}
