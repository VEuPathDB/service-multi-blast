package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithSeg;
import mb.lib.blast.model.Seg;
import mb.api.service.cli.CliBuilder;

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
