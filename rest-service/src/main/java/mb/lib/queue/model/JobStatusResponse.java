package mb.lib.queue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.lib.model.JobStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatusResponse
{
  private JobStatus status;

  @JsonSetter("status")
  public JobStatusResponse setStatus(JobStatus status) {
    this.status = status;
    return this;
  }

  @JsonIgnore
  public JobStatus getStatus() {
    return this.status;
  }
}
