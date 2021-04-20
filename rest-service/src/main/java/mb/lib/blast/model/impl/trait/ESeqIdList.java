package mb.lib.blast.model.impl.trait;

import java.io.File;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithSequenceIdList;
import mb.api.service.cli.CliBuilder;

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
