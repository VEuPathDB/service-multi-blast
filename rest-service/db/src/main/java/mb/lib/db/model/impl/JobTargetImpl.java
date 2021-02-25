package mb.lib.db.model.impl;

import java.util.List;

import mb.lib.db.model.JobTarget;

public class JobTargetImpl extends RowImpl implements JobTarget
{
  private final String organisms;
  private final String targetFiles;

  public JobTargetImpl(byte[] jobID, String organisms, String targetFiles) {
    super(jobID);
    this.organisms   = organisms;
    this.targetFiles = targetFiles;
  }

  @Override
  public String organism() {
    return organisms;
  }

  @Override
  public String targetFile() {
    return targetFiles;
  }
}
