package org.veupathdb.service.multiblast.extern;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.veupathdb.service.multiblast.Config;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;

class JobCreateRequest
{
  private Payload payload;

  public JobCreateRequest(Job job) {
    var cli = new CliBuilder();
    job.getConfig().toCli(cli);

    this.payload = new Payload(
      job.getJobId(),
      job.getTool().value(),
      cli.toArgArray()
    );
  }

  @JsonGetter("url")
  public String getUrl() {
    return Config.getInstance().getBlastHost();
  }

  @JsonGetter("payload")
  public Payload getPayload() {
    return payload;
  }

  public JobCreateRequest setPayload(Payload payload) {
    this.payload = payload;
    return this;
  }
}

class Payload {
  private final int      jobId;
  private final String   tool;
  private final String[] args;

  public Payload(int jobId, String tool, String[] args) {
    this.jobId = jobId;
    this.tool  = tool;
    this.args  = args;
  }

  @JsonGetter("jobId")
  public int getJobId() {
    return jobId;
  }

  @JsonGetter("tool")
  public String getTool() {
    return tool;
  }

  @JsonGetter("args")
  public String[] getArgs() {
    return args;
  }
}
