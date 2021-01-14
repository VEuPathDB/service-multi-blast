package mb.lib.extern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
class JobStatusResponse
{
  private String status;

  @JsonSetter("status")
  public JobStatusResponse setStatus(String status) {
    this.status = status;
    return this;
  }

  @JsonIgnore
  public JobStatus getStatus() {
    return JobStatus.fromString(this.status);
  }
}
