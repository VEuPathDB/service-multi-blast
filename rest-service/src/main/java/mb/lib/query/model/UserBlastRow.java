package mb.lib.query.model;

import java.time.OffsetDateTime;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.blast.BlastConfig;

public class UserBlastRow extends BlastRow
{
  private long    userID;
  private String  description;
  private long    maxDownloadSize;
  private boolean runDirectly;

  public UserBlastRow() {
  }

  public UserBlastRow(UserBlastRow old) {
    super(old);
    userID          = old.userID;
    description     = old.description;
    maxDownloadSize = old.maxDownloadSize;
    runDirectly     = old.runDirectly;
  }

  public UserBlastRow(BlastRow old) {
    super(old);
  }

  public long getUserID() {
    return userID;
  }

  public UserBlastRow setUserID(long userID) {
    this.userID = userID;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public UserBlastRow setDescription(String description) {
    this.description = description;
    return this;
  }

  public long getMaxDownloadSize() {
    return maxDownloadSize;
  }

  public UserBlastRow setMaxDownloadSize(long maxDownloadSize) {
    this.maxDownloadSize = maxDownloadSize;
    return this;
  }

  public boolean isRunDirectly() {
    return runDirectly;
  }

  public UserBlastRow setRunDirectly(boolean runDirectly) {
    this.runDirectly = runDirectly;
    return this;
  }

  @Override
  public String toString() {
    return "UserBlastRow{jobID=" + getJobID() + ", userID=" + getUserID() + "}";
  }

  /// Chaining Overrides

  @Override
  public UserBlastRow setJobID(HashID jobID) {
    return (UserBlastRow) super.setJobID(jobID);
  }

  @Override
  public UserBlastRow setConfig(BlastConfig config) {
    return (UserBlastRow) super.setConfig(config);
  }

  @Override
  public UserBlastRow setQuery(String query) {
    return (UserBlastRow) super.setQuery(query);
  }

  @Override
  public UserBlastRow setQueueID(int queueID) {
    return (UserBlastRow) super.setQueueID(queueID);
  }

  @Override
  public UserBlastRow setProjectID(String projectID) {
    return (UserBlastRow) super.setProjectID(projectID);
  }

  @Override
  public UserBlastRow setStatus(JobStatus status) {
    return (UserBlastRow) super.setStatus(status);
  }

  @Override
  public UserBlastRow setCreatedOn(OffsetDateTime createdOn) {
    return (UserBlastRow) super.setCreatedOn(createdOn);
  }

  @Override
  public UserBlastRow setDeleteOn(OffsetDateTime deleteOn) {
    return (UserBlastRow) super.setDeleteOn(deleteOn);
  }
}
