package org.veupathdb.service.multiblast.model.internal;

import java.time.OffsetDateTime;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.BlastTool;

public class Job
{
  private final int jobId;

  private final long userId;

  private final JobStatus status;

  private final BlastTool tool;

  private final OffsetDateTime createdOn;

  private final OffsetDateTime modifiedOn;

  private BlastConfig config;

  public Job(
    int jobId,
    long userId,
    JobStatus status,
    BlastTool tool,
    OffsetDateTime createdOn,
    OffsetDateTime modifiedOn
  ) {
    this.jobId      = jobId;
    this.userId     = userId;
    this.status     = status;
    this.tool       = tool;
    this.createdOn  = createdOn;
    this.modifiedOn = modifiedOn;
  }

  public int getJobId() {
    return jobId;
  }

  public long getUserId() {
    return userId;
  }

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public OffsetDateTime getModifiedOn() {
    return modifiedOn;
  }

  public JobStatus getStatus() {
    return status;
  }

  public boolean hasConfig() {
    return config != null;
  }

  public BlastConfig getConfig() {
    return config;
  }

  public Job setConfig(BlastConfig config) {
    this.config = config;
    return this;
  }

  public BlastTool getTool() {
    return tool;
  }
}
