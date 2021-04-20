package mb.lib.blast.model.trait;

import mb.api.service.cli.CliOptions;

public interface WithDbMasks<T> extends CliOptions
{
  String getDbSoftMaskAlgorithmId();
  T setDbSoftMaskAlgorithmId(String id);

  String getDbHardMaskAlgorithmId();
  T setDbHardMaskAlgorithmId(String id);
}
