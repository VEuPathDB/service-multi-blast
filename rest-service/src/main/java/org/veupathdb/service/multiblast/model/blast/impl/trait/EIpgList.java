package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithIdenticalProteinGroupList;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EIpgList implements WithIdenticalProteinGroupList<Void>
{
  private File ipgList;
  private File negativeIpgList;

  @Override
  public File getIdenticalProteinGroupListFile() {
    return ipgList;
  }

  @Override
  public File getNegativeIdenticalProteinGroupListFile() {
    return negativeIpgList;
  }

  @Override
  public Void setIdenticalProteinGroupListFile(File ipgList) {
    this.ipgList = ipgList;
    return null;
  }

  @Override
  public Void setNegativeIdenticalProteinGroupListFile(File negIpgList) {
    negativeIpgList = negIpgList;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.IdenticalProteinGroupListFile, ipgList)
      .appendNonNull(ToolOption.NegativeIdenticalProteinGroupListFile, negativeIpgList);
  }
}
