package mb.lib.blast.model.trait;

import mb.lib.blast.model.Seg;
import mb.api.service.cli.CliOptions;

public interface WithSeg<T> extends CliOptions
{
  Seg getSeg();
  T setSeg(Seg seg);
}
