package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithMaxIntronLength<T> extends CliOptions
{
  Integer getMaxIntronLength();
  T setMaxIntronLength(Integer len);
}
