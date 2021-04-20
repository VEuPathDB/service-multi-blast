package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithDbGeneticCode<T> extends CliOptions
{
  Byte getDbTranslationGeneticCode();
  T setDbTranslationGeneticCode(Byte i);
}
