package org.veupathdb.service.multiblast.model.blast.impl.trait;

import org.veupathdb.service.multiblast.model.blast.ToolOption;
import org.veupathdb.service.multiblast.model.blast.trait.WithGappedExtensionDropoff;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

public class EGapExtDrop implements WithGappedExtensionDropoff<Void>
{
  private Double prelimGapExtDropoff;
  private Double finalGapExtDropoff;

  @Override
  public Double getExtensionDropoffPreliminaryGapped() {
    return prelimGapExtDropoff;
  }

  @Override
  public Void setExtensionDropoffPreliminaryGapped(Double d) {
    prelimGapExtDropoff = d;
    return null;
  }

  @Override
  public Double getExtensionDropoffFinalGapped() {
    return finalGapExtDropoff;
  }

  @Override
  public Void setExtensionDropoffFinalGapped(Double d) {
    finalGapExtDropoff = d;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.ExtensionDropoffPrelimGapped, prelimGapExtDropoff)
      .appendNonNull(ToolOption.ExtensionDropoffFinalGapped, finalGapExtDropoff);
  }
}
