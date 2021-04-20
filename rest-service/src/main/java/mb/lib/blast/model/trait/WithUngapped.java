package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithUngapped<T> extends CliOptions
{
  boolean isUngappedAlignmentOnlyEnabled();
  T enableUngappedAlignmentOnly(boolean b);
}
