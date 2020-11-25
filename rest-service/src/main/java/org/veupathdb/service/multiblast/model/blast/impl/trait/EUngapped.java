package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithUngapped;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EUngapped implements WithUngapped<Void>
{
  private boolean ungapped;

  @Override
  public boolean isUngappedAlignmentOnlyEnabled() {
    return ungapped;
  }

  @Override
  public Void enableUngappedAlignmentOnly(boolean b) {
    this.ungapped = b;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    if (ungapped)
      cli.append(ToolOption.UngappedAlignmentOnly);
  }
}
