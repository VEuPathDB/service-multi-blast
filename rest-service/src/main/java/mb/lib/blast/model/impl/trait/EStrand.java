package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.QueryStrand;
import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithStrand;
import mb.api.service.cli.CliBuilder;

public class EStrand implements WithStrand<Void>
{
  private QueryStrand strand;

  @Override
  public QueryStrand getStrand() {
    return strand;
  }

  @Override
  public Void setStrand(QueryStrand s) {
    strand = s;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.QueryStrand, strand);
  }
}
