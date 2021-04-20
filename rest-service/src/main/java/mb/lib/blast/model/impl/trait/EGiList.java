package mb.lib.blast.model.impl.trait;

import java.io.File;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithGenInfoIdList;
import mb.api.service.cli.CliBuilder;

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
