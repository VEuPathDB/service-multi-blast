package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithWordSize;
import mb.api.service.cli.CliBuilder;

public class EWordSize implements WithWordSize<Void>
{
  public Integer wordSize;

  @Override
  public Integer getWordSize() {
    return wordSize;
  }

  @Override
  public Void setWordSize(Integer size) {
    wordSize = size;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.WordSize, wordSize);
  }
}
