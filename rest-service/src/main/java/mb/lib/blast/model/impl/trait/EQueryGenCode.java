package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithQueryGeneticCode;
import mb.api.service.cli.CliBuilder;

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
