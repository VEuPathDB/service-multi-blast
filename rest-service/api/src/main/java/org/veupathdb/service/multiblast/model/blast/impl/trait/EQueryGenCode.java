package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithQueryGeneticCode;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EQueryGenCode implements WithQueryGeneticCode<Void>
{
  private Byte queryGenCode;

  @Override
  public Byte getQueryTranslationGeneticCode() {
    return queryGenCode;
  }

  @Override
  public Void setQueryTranslationGeneticCode(Byte i) {
    this.queryGenCode = i;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.QueryGeneticCode, queryGenCode);
  }
}
