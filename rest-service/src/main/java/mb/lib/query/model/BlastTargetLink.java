package mb.lib.query.model;

import mb.lib.model.HashID;

public class BlastTargetLink
{
  private HashID jobID;
  private String organism;
  private String targetFile;

  public BlastTargetLink() {
  }

  public BlastTargetLink(HashID jobID, String organism, String targetFile) {
    this.jobID      = jobID;
    this.organism   = organism;
    this.targetFile = targetFile;
  }

  public HashID getJobID() {
    return jobID;
  }

  public BlastTargetLink setJobID(HashID jobID) {
    this.jobID = jobID;
    return this;
  }

  public String getOrganism() {
    return organism;
  }

  public BlastTargetLink setOrganism(String organism) {
    this.organism = organism;
    return this;
  }

  public String getTargetFile() {
    return targetFile;
  }

  public BlastTargetLink setTargetFile(String targetFile) {
    this.targetFile = targetFile;
    return this;
  }
}
