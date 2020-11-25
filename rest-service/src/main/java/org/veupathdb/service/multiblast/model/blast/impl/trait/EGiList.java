package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithGenInfoIdList;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EGiList implements WithGenInfoIdList<Void>
{
  private File giList;
  private File negativeGiList;

  @Override
  public File getGenInfoIdListFile() {
    return giList;
  }

  @Override
  public Void setGenInfoIdListFile(File f) {
    giList = f;
    return null;
  }

  @Override
  public File getNegativeGenInfoIdListFile() {
    return negativeGiList;
  }

  @Override
  public Void setNegativeGenInfoIdListFile(File f) {
    negativeGiList = f;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.GIListFile, giList)
      .appendNonNull(ToolOption.NegativeGIListFile, negativeGiList);
  }
}
