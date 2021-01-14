package mb.lib.extern;

import com.fasterxml.jackson.annotation.JsonGetter;

class JobCreateRequest
{
  private final String host;
  private final Payload payload;

  public JobCreateRequest(String host, String jobID, String[] cli) {
    this.payload = new Payload(jobID, cli);
    this.host = host;
  }

  @JsonGetter("url")
  public String getUrl() {
    return this.host;
  }

  @JsonGetter("payload")
  public Payload getPayload() {
    return payload;
  }
}

class Payload {
  private final String   jobId;
  private final String[] args;

  public Payload(String jobId, String[] args) {
    this.jobId = jobId;
    this.args  = args;
  }

  @JsonGetter("jobId")
  public String getJobId() {
    return jobId;
  }

  @JsonGetter("args")
  public String[] getArgs() {
    return args;
  }
}
