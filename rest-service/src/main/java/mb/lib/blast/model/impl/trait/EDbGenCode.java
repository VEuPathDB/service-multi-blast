package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithDbGeneticCode;
import mb.api.service.cli.CliBuilder;

public class EDbGenCode implements WithDbGeneticCode<Void>
{
  /**
   * <pre>
   * -db_gencode <Integer, values between: 1-6, 9-16, 21-31, 33>
   *   Genetic code to use to translate database/subjects (see user manual for
   *   details)
   * </pre>
   * <p>
   * Default = `1'
   */
  private Byte geneticCode;

  @Override
  public Byte getDbTranslationGeneticCode() {
    return geneticCode;
  }

  @Override
  public Void setDbTranslationGeneticCode(Byte i) {
    geneticCode = i;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.DatabaseTranslationGenCode, geneticCode);
  }
}
