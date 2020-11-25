package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithUngapped<T> extends CliOptions
{
  boolean isUngappedAlignmentOnlyEnabled();
  T enableUngappedAlignmentOnly(boolean b);
}
