package mb.lib.querier;

import com.fasterxml.jackson.annotation.JsonGetter;
import mb.api.model.io.JsonKeys;
import mb.lib.model.HashID;
import org.veupathdb.lib.blast.BlastConfig;
import org.veupathdb.lib.blast.BlastTool;

public class BlastRequest
{
  private final HashID jobID;
  private final BlastTool tool;
  private final BlastConfig config;

  public BlastRequest(HashID jobID, BlastTool tool, BlastConfig config) {
    this.jobID  = jobID;
    this.tool   = tool;
    this.config = config;
  }

  @JsonGetter(JsonKeys.JobID)
  public HashID getJobID() {
    return jobID;
  }

  @JsonGetter(JsonKeys.Tool)
  public BlastTool getTool() {
    return tool;
  }

  @JsonGetter(JsonKeys.Config)
  public BlastConfig getConfig() {
    return config;
  }
}
