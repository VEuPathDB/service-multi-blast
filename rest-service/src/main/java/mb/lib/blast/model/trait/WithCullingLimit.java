package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithCullingLimit<T> extends CliOptions
{
  Integer getCullingLimit();
  T setCullingLimit(Integer v);
}
