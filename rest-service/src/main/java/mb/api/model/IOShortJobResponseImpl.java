package mb.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import mb.lib.model.JobStatus;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IOShortJobResponseImpl implements IOShortJobResponse
{
  private String        id;
  private String        description;
  private JobStatus     status;
  private String        created;
  private String        expires;
  private Long          maxResultSize;
  private IOJobLink[]   parentJobs;
  private IOJobLink[]   childJobs;
  private boolean       isPrimary;
  private String        site;
  private IOJobTarget[] targets;

  @Override
  public String getId() {
    return this.id;
  }

  @Override
  public IOShortJobResponse setId(String id) {
    this.id = id;
    return this;
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public IOShortJobResponse setDescription(String description) {
    this.description = description;
    return this;
  }

  @Override
  public JobStatus getStatus() {
    return this.status;
  }

  @Override
  public IOShortJobResponse setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  @Override
  public String getCreated() {
    return created;
  }

  @Override
  public IOShortJobResponse setCreated(String created) {
    this.created = created;
    return this;
  }

  @Override
  public String getExpires() {
    return expires;
  }

  @Override
  public IOShortJobResponse setExpires(String expires) {
    this.expires = expires;
    return this;
  }

  @Override
  public Long getMaxResultSize() {
    return maxResultSize;
  }

  @Override
  public IOShortJobResponse setMaxResultSize(Long maxResultSize) {
    this.maxResultSize = maxResultSize;
    return this;
  }

  @Override
  public IOJobLink[] getParentJobs() {
    return parentJobs;
  }

  @Override
  public IOShortJobResponse setParentJobs(IOJobLink[] parentJobID) {
    this.parentJobs = parentJobID;
    return this;
  }

  @Override
  public boolean getIsPrimary() {
    return isPrimary;
  }

  @Override
  public IOShortJobResponse setIsPrimary(boolean isPrimary) {
    this.isPrimary = isPrimary;
    return this;
  }

  @Override
  public String getSite() {
    return site;
  }

  @Override
  public IOShortJobResponse setSite(String site) {
    this.site = site;
    return this;
  }

  @Override
  public IOJobTarget[] getTargets() {
    return targets;
  }

  @Override
  public IOShortJobResponse setTargets(IOJobTarget[] targets) {
    this.targets = targets;
    return this;
  }

  public IOJobLink[] getChildJobs() {
    return childJobs;
  }

  public IOShortJobResponse setChildJobs(IOJobLink[] childJobs) {
    this.childJobs = childJobs;
    return this;
  }
}
