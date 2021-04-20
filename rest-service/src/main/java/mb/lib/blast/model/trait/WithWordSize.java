package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithWordSize<T> extends CliOptions
{
  Integer getWordSize();
  T setWordSize(Integer size);
}
