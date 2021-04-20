package mb.lib.blast.model.impl.trait;

import mb.lib.blast.model.ToolOption;
import mb.lib.blast.model.trait.WithDbMasks;
import mb.api.service.cli.CliBuilder;

public class EDbMask implements WithDbMasks<Void>
{
  private String dbSoftMask;
  private String dbHardMask;

  @Override
  public String getDbSoftMaskAlgorithmId() {
    return dbSoftMask;
  }

  @Override
  public Void setDbSoftMaskAlgorithmId(String id) {
    dbSoftMask = id;
    return null;
  }

  @Override
  public String getDbHardMaskAlgorithmId() {
    return dbHardMask;
  }

  @Override
  public Void setDbHardMaskAlgorithmId(String id) {
    dbHardMask = id;
    return null;
  }

  @Override
  public void toCli(CliBuilder cli) {
    cli.appendNonNull(ToolOption.DatabaseSoftMask, dbSoftMask)
      .appendNonNull(ToolOption.DatabaseHardMask, dbHardMask);
  }
}
