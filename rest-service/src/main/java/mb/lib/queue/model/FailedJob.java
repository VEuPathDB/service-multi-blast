package mb.lib.queue.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;

public class FailedJob
{
  private int failID;
  private int jobID;
  private String category;
  private String url;
  private String[] payload;
  private JobResult result;
  private int            failCount;
  private OffsetDateTime failedAt;
  private OffsetDateTime createdAt;

  @JsonGetter(JsonKeys.ID)
  public int getFailID() {
    return failID;
  }

  @JsonSetter(JsonKeys.ID)
  public FailedJob setFailID(int failID) {
    this.failID = failID;
    return this;
  }

  @JsonGetter(JsonKeys.JobID)
  public int getJobID() {
    return jobID;
  }

  @JsonSetter(JsonKeys.JobID)
  public FailedJob setJobID(int jobID) {
    this.jobID = jobID;
    return this;
  }

  @JsonGetter(JsonKeys.Category)
  public String getCategory() {
    return category;
  }

  @JsonSetter(JsonKeys.Category)
  public FailedJob setCategory(String category) {
    this.category = category;
    return this;
  }

  @JsonGetter(JsonKeys.URL)
  public String getUrl() {
    return url;
  }

  @JsonSetter(JsonKeys.URL)
  public FailedJob setUrl(String url) {
    this.url = url;
    return this;
  }

  @JsonGetter(JsonKeys.Payload)
  public String[] getPayload() {
    return payload;
  }

  @JsonSetter(JsonKeys.Payload)
  public FailedJob setPayload(String[] payload) {
    this.payload = payload;
    return this;
  }

  @JsonGetter(JsonKeys.Result)
  public JobResult getResult() {
    return result;
  }

  @JsonSetter(JsonKeys.Result)
  public FailedJob setResult(JobResult result) {
    this.result = result;
    return this;
  }

  @JsonGetter(JsonKeys.FailCount)
  public int getFailCount() {
    return failCount;
  }

  @JsonSetter(JsonKeys.FailCount)
  public FailedJob setFailCount(int failCount) {
    this.failCount = failCount;
    return this;
  }

  @JsonGetter(JsonKeys.FailedAt)
  public OffsetDateTime getFailedAt() {
    return failedAt;
  }

  @JsonSetter(JsonKeys.FailedAt)
  public FailedJob setFailedAt(OffsetDateTime failedAt) {
    this.failedAt = failedAt;
    return this;
  }

  @JsonGetter(JsonKeys.CreatedAt)
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @JsonSetter(JsonKeys.CreatedAt)
  public FailedJob setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }
}
