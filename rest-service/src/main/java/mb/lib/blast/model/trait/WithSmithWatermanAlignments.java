package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithSmithWatermanAlignments<T> extends CliOptions
{
  boolean isSmithWatermanTracebackEnabled();
  T enableSmithWatermanTraceback(boolean b);
}
