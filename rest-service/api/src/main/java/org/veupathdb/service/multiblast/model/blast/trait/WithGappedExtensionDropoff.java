package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithGappedExtensionDropoff<T> extends CliOptions
{
  Double getExtensionDropoffPreliminaryGapped();
  T setExtensionDropoffPreliminaryGapped(Double d);

  Double getExtensionDropoffFinalGapped();
  T setExtensionDropoffFinalGapped(Double d);
}
