package org.veupathdb.service.multiblast.model.blast.impl.trait;

import java.io.File;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithSequenceIdList;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ESeqIdList implements WithSequenceIdList<Void>
{
  private File seqIdList;
  private File negativeSeqIdList;

  @Override
  public File getSequenceIdListFile() {
    return seqIdList;
  }

  @Override
  public Void setSequenceIDListFile(File f) {
    seqIdList = f;
    return null;
  }

  @Override
  public File getNegativeSequenceIdListFile() {
    return negativeSeqIdList;
  }

  @Override
  public Void setNegativeSequenceIDListFile(File f) {
    negativeSeqIdList = f;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.SequenceIDListFile, seqIdList)
      .appendNonNull(ToolOption.NegativeSequenceIDListFile, negativeSeqIdList);
  }
}
