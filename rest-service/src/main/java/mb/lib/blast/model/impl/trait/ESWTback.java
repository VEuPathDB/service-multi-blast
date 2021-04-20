package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithSmithWatermanAlignments;
import mb.api.service.cli.CliBuilder;

public class ESWTback implements WithSmithWatermanAlignments<Void>
{
  private boolean swTraceback;

  @Override
  public boolean isSmithWatermanTracebackEnabled() {
    return swTraceback;
  }

  @Override
  public Void enableSmithWatermanTraceback(boolean b) {
    swTraceback = b;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    if (swTraceback)
      cli.append(ToolOption.UseSmithWatermanAlignments);
  }
}
