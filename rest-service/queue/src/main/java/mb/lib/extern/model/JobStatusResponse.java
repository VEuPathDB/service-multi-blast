package mb.lib.extern.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStatusResponse
{
  private String status;

  @JsonSetter("status")
  public JobStatusResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  @JsonIgnore
  public QueueJobStatus getStatus() {
    return QueueJobStatus.fromString(this.status);
  }
}
