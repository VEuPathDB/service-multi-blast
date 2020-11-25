package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithQueryGeneticCode<T> extends CliOptions
{
  Byte getQueryTranslationGeneticCode();
  T setQueryTranslationGeneticCode(Byte i);
}
