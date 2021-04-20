package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithQueryGeneticCode<T> extends CliOptions
{
  Byte getQueryTranslationGeneticCode();
  T setQueryTranslationGeneticCode(Byte i);
}
