package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithSeg<T> extends CliOptions
{
  Seg getSeg();
  T setSeg(Seg seg);
}
