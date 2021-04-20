package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithGappedExtensionDropoff<T> extends CliOptions
{
  Double getExtensionDropoffPreliminaryGapped();
  T setExtensionDropoffPreliminaryGapped(Double d);

  Double getExtensionDropoffFinalGapped();
  T setExtensionDropoffFinalGapped(Double d);
}
