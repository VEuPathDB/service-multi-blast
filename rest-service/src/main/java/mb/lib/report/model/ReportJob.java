package mb.lib.report.model;

import mb.lib.model.HashID;
import mb.lib.util.JSON;
import mb.lib.util.MD5;
import org.veupathdb.lib.blast.BlastFormatter;

public class ReportJob
{
  private final HashID         jobID;
  private final long           userID;
  private final BlastFormatter config;
  private final String         description;

  private HashID reportID;

  public ReportJob(HashID jobID, long userID, BlastFormatter config, String description) {
    this.jobID       = jobID;
    this.userID      = userID;
    this.config      = config;
    this.description = description;
  }

  public ReportJob(
    HashID jobID,
    HashID reportID,
    long userID,
    BlastFormatter config,
    String description
  ) {
    this.jobID       = jobID;
    this.userID      = userID;
    this.config      = config;
    this.description = description;
  }

  public HashID getJobID() {
    return jobID;
  }

  public long getUserID() {
    return userID;
  }

  public BlastFormatter getConfig() {
    return config;
  }

  public String getDescription() {
    return description;
  }

  public HashID getReportID() {
    if (reportID == null)
      reportID = generateReportID();

    return reportID;
  }

  @Override
  public String toString() {
    return "ReportJob{jobID=" + getJobID() + ", reportID=" + getReportID() + "}";
  }

  private HashID generateReportID() {
    try {
      return new HashID(MD5.hash(JSON.stringify(new HashWrapper(jobID, config))));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static class HashWrapper
  {
    private final HashID         jobID;
    private final BlastFormatter config;

    private HashWrapper(HashID jobID, BlastFormatter config) {
      this.jobID  = jobID;
      this.config = config;
    }

    public HashID getJobID() {
      return jobID;
    }

    public BlastFormatter getConfig() {
      return config;
    }
  }
}
