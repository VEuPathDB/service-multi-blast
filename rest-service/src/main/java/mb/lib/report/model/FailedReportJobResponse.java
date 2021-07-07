package mb.lib.report.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;
import mb.lib.queue.model.FailedJobResponse;

public class FailedReportJobResponse implements FailedJobResponse<FailedReportJob>
{
  private List<FailedReportJob> failedJobs;

  @JsonGetter(JsonKeys.FailedJobs)
  public List<FailedReportJob> getFailedJobs() {
    return failedJobs;
  }

  @JsonSetter(JsonKeys.FailedJobs)
  public FailedJobResponse<FailedReportJob> setFailedJobs(List<FailedReportJob> failedJobs) {
    this.failedJobs = failedJobs;
    return this;
  }
}
