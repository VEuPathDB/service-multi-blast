package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithSmithWatermanAlignments;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

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
