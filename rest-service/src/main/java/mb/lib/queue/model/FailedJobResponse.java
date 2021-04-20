package mb.lib.queue.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FailedJobResponse
{
  private List<FailedJob> failedJobs;

  @JsonGetter(JsonKeys.FailedJobs)
  public List<FailedJob> getFailedJobs() {
    return failedJobs;
  }

  @JsonSetter(JsonKeys.FailedJobs)
  public FailedJobResponse setFailedJobs(List<FailedJob> failedJobs) {
    this.failedJobs = failedJobs;
    return this;
  }
}
