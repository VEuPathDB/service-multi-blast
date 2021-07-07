package mb.lib.queue.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.queue.consts.JsonKeys;

@JsonIgnoreProperties(ignoreUnknown = true)
public interface FailedJobResponse<T extends FailedJob<?>>
{
  @JsonGetter(JsonKeys.FailedJobs)
  List<T> getFailedJobs();

  @JsonSetter(JsonKeys.FailedJobs)
  FailedJobResponse<T> setFailedJobs(List<T> failedJobs);
}
