package mb.lib.query.model;

import java.time.OffsetDateTime;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.blast.BlastConfig;

public class BlastRow
{
  private HashID         jobID;
  private BlastConfig    config;
  private String         query;
  private int            queueID;
  private String         projectID;
  private JobStatus      status;
  private OffsetDateTime createdOn;
  private OffsetDateTime deleteOn;

  public BlastRow() {
  }

  public BlastRow(BlastRow old) {
    jobID     = old.jobID;
    config    = old.config;
    query     = old.query;
    queueID   = old.queueID;
    projectID = old.projectID;
    status    = old.status;
    createdOn = old.createdOn;
    deleteOn  = old.deleteOn;
  }

  public HashID getJobID() {
    return jobID;
  }

  public BlastRow setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }

  public BlastConfig getConfig() {
    return config;
  }

  public BlastRow setConfig(BlastConfig config) {
    this.config = config;
    return this;
  }

  public String getQuery() {
    return query;
  }

  public BlastRow setQuery(String query) {
    this.query = query;
    return this;
  }

  public int getQueueID() {
    return queueID;
  }

  public BlastRow setQueueID(int queueID) {
    this.queueID = queueID;
    return this;
  }

  public String getProjectID() {
    return projectID;
  }

  public BlastRow setProjectID(String projectID) {
    this.projectID = projectID;
    return this;
  }

  public JobStatus getStatus() {
    return status;
  }

  public BlastRow setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  public OffsetDateTime getCreatedOn() {
    return createdOn;
  }

  public BlastRow setCreatedOn(OffsetDateTime createdOn) {
    this.createdOn = createdOn;
    return this;
  }

  public OffsetDateTime getDeleteOn() {
    return deleteOn;
  }

  public BlastRow setDeleteOn(OffsetDateTime deleteOn) {
    this.deleteOn = deleteOn;
    return this;
  }

  @Override
  public String toString() {
    return "BlastRow{jobID=" + getJobID() + '}';
  }
}
