package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithUngapped;
import mb.api.service.cli.CliBuilder;

public class EUngapped implements WithUngapped<Void>
{
  private boolean ungapped;

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return ungapped;
  }

  @Override
  public Void enableUngappedAlignmentOnly(boolean b) {
    this.ungapped = b;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    if (ungapped)
      cli.append(ToolOption.UngappedAlignmentOnly);
  }
}
