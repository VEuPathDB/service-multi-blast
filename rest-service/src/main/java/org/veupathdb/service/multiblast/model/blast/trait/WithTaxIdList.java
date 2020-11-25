package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithTaxIdList <T> extends CliOptions
{
  File getTaxIdListFile();
  T setTaxIdListFile(File f);

  File getNegativeTaxIdListFile();
  T setNegativeTaxIdListFile(File f);
}
