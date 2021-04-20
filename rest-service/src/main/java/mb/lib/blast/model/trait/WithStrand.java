package mb.lib.blast.model.trait;

import mb.lib.blast.model.QueryStrand;
import mb.api.service.cli.CliOptions;

public interface WithStrand<T> extends CliOptions
{
  QueryStrand getStrand();
  T setStrand(QueryStrand s);
}
