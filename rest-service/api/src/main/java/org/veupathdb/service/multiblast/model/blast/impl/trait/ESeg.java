package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.Seg;
import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithSeg;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class ESeg implements WithSeg<Void>
{
  private Seg seg;

  @Override
  public Seg getSeg() {
    return seg;
  }

  @Override
  public Void setSeg(Seg seg) {
    this.seg = seg;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.SEGFilter, seg);
  }
}
