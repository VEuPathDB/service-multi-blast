package mb.lib.queue.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatusResponse
{
  private QueueJobStatus status;

  @JsonSetter("status")
  public JobStatusResponse setStatus(QueueJobStatus status) {
    this.status = status;
    return this;
  }

  @JsonIgnore
  public QueueJobStatus getStatus() {
    return this.status;
  }
}
