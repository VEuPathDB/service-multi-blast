package mb.lib.query.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;
import mb.lib.queue.model.FailedJobResponse;

public class FailedQueryJobResponse implements FailedJobResponse<FailedQueryJob>
{
  private List<FailedQueryJob> failedJobs;

  @JsonGetter(JsonKeys.FailedJobs)
  public List<FailedQueryJob> getFailedJobs() {
    return failedJobs;
  }

  @JsonSetter(JsonKeys.FailedJobs)
  public FailedJobResponse<FailedQueryJob> setFailedJobs(List<FailedQueryJob> failedJobs) {
    this.failedJobs = failedJobs;
    return this;
  }
}
