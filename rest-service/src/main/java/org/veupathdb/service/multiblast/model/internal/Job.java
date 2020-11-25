package org.veupathdb.service.multiblast.model.internal;

import java.time.OffsetDateTime;

import org.veupathdb.service.multiblast.model.blast.BlastConfig;
import org.veupathdb.service.multiblast.model.blast.BlastTool;

public class Job
{
  private int jobId;

  private final long userId;

  private final JobStatus status;

  private final BlastTool tool;

  private OffsetDateTime createdOn;

  private OffsetDateTime modifiedOn;

  private BlastConfig config;

  public Job(
    long userId,
    JobStatus status,
    BlastTool tool
  ) {
    this.userId     = userId;
    this.status     = status;
    this.tool       = tool;
  }

  public int getJobId() {
    return jobId;
  }

  public Job setJobId(int jobId) {
    this.jobId = jobId;
    return this;
  }

  public long getUserId() {
    return userId;
  }

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public Job setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  public OffsetDateTime getModifiedOn() {
    return modifiedOn;
  }

  public Job setModifiedOn(OffsetDateTime modifiedOn) {
    this.modifiedOn = modifiedOn;
    return this;
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
