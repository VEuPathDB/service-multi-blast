package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithMaxIntronLength<T> extends CliOptions
{
  Integer getMaxIntronLength();
  T setMaxIntronLength(Integer len);
}
