package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithStrand;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

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
