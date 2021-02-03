package org.veupathdb.service.multiblast.model.blast.trait;

import java.io.File;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithTaxIdList <T> extends CliOptions
{
  File getTaxIdListFile();
  T setTaxIDListFile(File f);

  File getNegativeTaxIdListFile();
  T setNegativeTaxIDListFile(File f);
}
