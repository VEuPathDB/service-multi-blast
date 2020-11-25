package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithSmithWatermanAlignments<T> extends CliOptions
{
  boolean isSmithWatermanTracebackEnabled();
  T enableSmithWatermanTraceback(boolean b);
}
