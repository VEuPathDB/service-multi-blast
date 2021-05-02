package mb.lib.query.model;

import mb.lib.model.HashID;
import mb.lib.util.JSON;
import mb.lib.util.MD5;
import org.veupathdb.lib.blast.BlastBase;
import org.veupathdb.lib.blast.BlastQueryConfig;
import org.veupathdb.lib.blast.BlastTool;

public class BlastJob
{
  private final String           site;
  private final long             userID;
  private final BlastQueryConfig config;
  private final String           query;
  private final String           projectID;
  private final String           description;
  private final long             maxDownloadSize;

  private HashID jobID;

  public BlastJob(
    String site,
    long userID,
    BlastQueryConfig config,
    String query,
    String projectID,
    String description,
    long maxDownloadSize
  ) {
    this.site            = site;
    this.userID          = userID;
    this.config          = config;
    this.query           = query;
    this.projectID       = projectID;
    this.description     = description;
    this.maxDownloadSize = maxDownloadSize;
  }

  public BlastJob(
    String site,
    long userID,
    BlastQueryConfig config,
    String query,
    String projectID,
    long maxDownloadSize
  ) {
    this(site, userID, config, null, query, projectID, maxDownloadSize);
  }

  public long getUserID() {
    return userID;
  }

  public BlastQueryConfig getConfig() {
    return config;
  }

  public String getDescription() {
    return description;
  }

  public String getSite() {
    return site;
  }

  public long getMaxDownloadSize() {
    return maxDownloadSize;
  }

  public HashID getJobID() {
    if (jobID == null)
      jobID = generateJobID();
    return jobID;
  }

  public String getQuery() {
    return query;
  }

  public String getProjectID() {
    return projectID;
  }

  private HashID generateJobID() {
    try {
      return new HashID(MD5.hash(JSON.stringify(new HashWrapper(site, config))));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static class HashWrapper
  {
    private final BlastTool   tool;
    private final String      site;
    private final String      dbFiles;
    private final BlastQueryConfig config;

    public HashWrapper(String site, BlastQueryConfig config) {
      this.tool    = config.getTool();
      this.site    = site;
      this.dbFiles = ((BlastBase) config).getDBFile();
      this.config  = config;
    }

    public BlastTool getTool() {
      return tool;
    }

    public String getSite() {
      return site;
    }

    public String getDbFiles() {
      return dbFiles;
    }

    public BlastQueryConfig getConfig() {
      return config;
    }
  }
}
