package mb.lib.db.model.impl;

import mb.lib.db.model.JobTarget;

public class JobTargetImpl extends RowImpl implements JobTarget
{
  private final String organism;
  private final String targetFile;

  public JobTargetImpl(byte[] jobID, String organism, String targetFile) {
    super(jobID);
    this.organism   = organism;
    this.targetFile = targetFile;
  }

  @Override
  public String organism() {
    return organism;
  }

  @Override
  public String targetFile() {
    return targetFile;
  }

  @Override
  public String toString() {
    return "JobTarget{" +
      "hash=" + printID() + '\'' +
      ", organism='" + organism + '\'' +
      ", targetFile='" + targetFile + '\'' +
      '}';
  }
}
