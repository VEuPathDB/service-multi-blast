package mb.lib.query.model;

import java.time.OffsetDateTime;
import java.util.List;

import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.blast.BlastConfig;

public class FullUserBlastRow extends UserBlastRow
{
  private List<BlastTargetLink> targetDBs;
  private List<BlastJobLink>    childJobs;
  private List<BlastJobLink>    parentJobs;

  public FullUserBlastRow() {
  }

  public FullUserBlastRow(UserBlastRow row) {
    super(row);
  }

  public FullUserBlastRow(BlastRow row) {
    super(row);
  }

  public List<BlastTargetLink> getTargetDBs() {
    return targetDBs;
  }

  public FullUserBlastRow setTargetDBs(List<BlastTargetLink> targetDBs) {
    this.targetDBs = targetDBs;
    return this;
  }

  public List<BlastJobLink> getChildJobs() {
    return childJobs;
  }

  public FullUserBlastRow setChildJobs(List<BlastJobLink> childJobs) {
    this.childJobs = childJobs;
    return this;
  }

  public List<BlastJobLink> getParentJobs() {
    return parentJobs;
  }

  public FullUserBlastRow setParentJobs(List<BlastJobLink> parentJobs) {
    this.parentJobs = parentJobs;
    return this;
  }

  @Override
  public String toString() {
    return "FullUserBlastRow{jobID=" + getJobID() + ", userID=" + getUserID() + "}";
  }

  /// Chaining Overrides


  @Override
  public FullUserBlastRow setUserID(long userID) {
    return (FullUserBlastRow) super.setUserID(userID);
  }

  @Override
  public FullUserBlastRow setDescription(String description) {
    return (FullUserBlastRow) super.setDescription(description);
  }

  @Override
  public FullUserBlastRow setMaxDownloadSize(long maxDownloadSize) {
    return (FullUserBlastRow) super.setMaxDownloadSize(maxDownloadSize);
  }

  @Override
  public FullUserBlastRow setRunDirectly(boolean runDirectly) {
    return (FullUserBlastRow) super.setRunDirectly(runDirectly);
  }

  @Override
  public FullUserBlastRow setJobID(HashID jobID) {
    return (FullUserBlastRow) super.setJobID(jobID);
  }

  @Override
  public FullUserBlastRow setConfig(BlastConfig config) {
    return (FullUserBlastRow) super.setConfig(config);
  }

  @Override
  public FullUserBlastRow setQuery(String query) {
    return (FullUserBlastRow) super.setQuery(query);
  }

  @Override
  public FullUserBlastRow setQueueID(int queueID) {
    return (FullUserBlastRow) super.setQueueID(queueID);
  }

  @Override
  public FullUserBlastRow setProjectID(String projectID) {
    return (FullUserBlastRow) super.setProjectID(projectID);
  }

  @Override
  public FullUserBlastRow setStatus(JobStatus status) {
    return (FullUserBlastRow) super.setStatus(status);
  }

  @Override
  public FullUserBlastRow setCreatedOn(OffsetDateTime createdOn) {
    return (FullUserBlastRow) super.setCreatedOn(createdOn);
  }

  @Override
  public FullUserBlastRow setDeleteOn(OffsetDateTime deleteOn) {
    return (FullUserBlastRow) super.setDeleteOn(deleteOn);
  }
}
