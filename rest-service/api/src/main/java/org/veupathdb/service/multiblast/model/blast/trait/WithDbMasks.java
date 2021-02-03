package org.veupathdb.service.multiblast.model.blast.trait;

import org.veupathdb.service.multiblast.service.cli.CliOptions;

public interface WithDbMasks<T> extends CliOptions
{
  String getDbSoftMaskAlgorithmId();
  T setDbSoftMaskAlgorithmId(String id);

  String getDbHardMaskAlgorithmId();
  T setDbHardMaskAlgorithmId(String id);
}
