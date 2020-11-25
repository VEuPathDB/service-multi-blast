package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.model.blast.QueryStrand;
import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithStrand<T> extends CliOptions
{
  QueryStrand getStrand();
  T setStrand(QueryStrand s);
}
