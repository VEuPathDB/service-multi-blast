package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithDbGeneticCode<T> extends CliOptions
{
  Byte getDbTranslationGeneticCode();
  T setDbTranslationGeneticCode(Byte i);
}
