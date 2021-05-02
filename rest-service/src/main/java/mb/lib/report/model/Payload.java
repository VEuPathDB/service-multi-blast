package mb.lib.report.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.api.model.io.JsonKeys;
import mb.lib.model.HashID;
import org.veupathdb.lib.blast.BlastFormatter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Payload
{
  private HashID         jobID;
  private BlastFormatter config;

  public Payload(HashID jobID, BlastFormatter config) {
    this.jobID  = jobID;
    this.config = config;
  }

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonSetter(JsonKeys.JobID)
  public Payload setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }

  @JsonGetter(JsonKeys.Config)
  public BlastFormatter getConfig() {
    return config;
  }

  @JsonSetter(JsonKeys.Config)
  public Payload setConfig(BlastFormatter config) {
    this.config = config;
    return this;
  }
}
