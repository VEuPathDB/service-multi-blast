package mb.lib.queue.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FailedJobResponse<T extends FailedJob<?>>
{
  private List<T> failedJobs;

  @JsonGetter(JsonKeys.FailedJobs)
  public List<T> getFailedJobs() {
    return failedJobs;
  }

  @JsonSetter(JsonKeys.FailedJobs)
  public FailedJobResponse<T> setFailedJobs(List<T> failedJobs) {
    this.failedJobs = failedJobs;
    return this;
  }
}
