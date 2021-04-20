package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithMaxIntronLength;
import mb.api.service.cli.CliBuilder;

public class EMaxIntronLength implements WithMaxIntronLength<Void>
{
  private Integer maxIntronLength;

  @Override
  public Integer getMaxIntronLength() {
    return maxIntronLength;
  }

  @Override
  public Void setMaxIntronLength(Integer len) {
    maxIntronLength = len;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.MaxIntronLength, maxIntronLength);
  }
}
