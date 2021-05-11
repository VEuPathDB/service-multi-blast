package mb.api.model.internal;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import mb.api.model.io.JsonKeys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.BlastConfig;
import org.veupathdb.lib.blast.BlastTool;

public class Job
{
  private static final Logger Log = LogManager.getLogger(Job.class);

  private final BlastTool tool;

  private OffsetDateTime createdOn;

  private BlastConfig config;

  private String project;

  public Job(BlastTool tool) {
    Log.trace("::new(tool={})", tool);
    this.tool = tool;
  }

  @JsonIgnore
  public OffsetDateTime getCreatedOn() {
    Log.trace("#getCreatedOn()");
    return createdOn;
  }

  @JsonIgnore
  public Job setCreatedOn(OffsetDateTime createdOn) {
    Log.trace("#setCreatedOn(createdOn={})", createdOn);
    this.createdOn = createdOn;
    return this;
  }

  @JsonIgnore
  public boolean hasConfig() {
    Log.trace("#hasConfig()");
    return config != null;
  }

  @JsonGetter(JsonKeys.Config)
  public BlastConfig getJobConfig() {
    Log.trace("#getJobConfig()");
    return config;
  }

  @JsonSetter(JsonKeys.Config)
  public Job setJobConfig(BlastConfig config) {
    Log.trace("#setJobConfig(config={})", config);
    this.config = config;
    return this;
  }

  @JsonGetter(JsonKeys.Tool)
  public BlastTool getTool() {
    Log.trace("#getTool()");
    return tool;
  }

  @JsonGetter(JsonKeys.ProjectID)
  public String getProject() {
    Log.trace("#getProject()");
    return project;
  }

  @JsonSetter(JsonKeys.ProjectID)
  public void setProject(String project) {
    Log.trace("#setProject(project={})", project);
    this.project = project;
  }
}
