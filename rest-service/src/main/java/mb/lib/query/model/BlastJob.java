package mb.lib.query.model;

import mb.lib.blast.model.BlastQuery;
import mb.lib.model.HashID;
import mb.lib.util.JSON;
import mb.lib.util.MD5;
import org.veupathdb.lib.blast.BlastQueryConfig;
import org.veupathdb.lib.blast.BlastTool;

public class BlastJob
{
  private BlastQueryConfig config;
  private BlastQuery       query;
  private String           site;
  private String           description;
  private long             userID;
  private long             maxDLSize;
  private HashID           parentID;
  private JobTarget[]      targets;

  public String getSite() {
    return site;
  }

  public BlastJob setSite(String site) {
    this.site = site;
    return this;
  }

  public BlastQuery getQuery() {
    return query;
  }

  public BlastJob setQuery(BlastQuery query) {
    this.query = query;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public BlastJob setDescription(String description) {
    this.description = description;
    return this;
  }

  public long getUserID() {
    return userID;
  }

  public BlastJob setUserID(long userID) {
    this.userID = userID;
    return this;
  }

  public long getMaxDLSize() {
    return maxDLSize;
  }

  public BlastJob setMaxDLSize(long maxDLSize) {
    this.maxDLSize = maxDLSize;
    return this;
  }

  public boolean isPrimary() {
    return parentID == null;
  }

  public HashID getParentID() {
    return parentID;
  }

  public BlastJob setParentID(HashID parentID) {
    this.parentID = parentID;
    return this;
  }

  public JobTarget[] getTargets() {
    return targets;
  }

  public BlastJob setTargets(JobTarget[] targets) {
    this.targets = targets;
    return this;
  }

  public BlastQueryConfig getConfig() {
    return config;
  }

  public BlastJob setConfig(BlastQueryConfig config) {
    this.config = config;
    return this;
  }

  public HashID digest(String query) {
    try {
      return new HashID(MD5.hash(JSON.stringify(new HashWrapper(site, query, config))));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  static class HashWrapper
  {
    private final BlastTool        tool;
    private final String           site;
    private final String           dbFiles;
    private final String           query;
    private final BlastQueryConfig config;

    public HashWrapper(String site, String query, BlastQueryConfig config) {
      this.tool    = config.getTool();
      this.site    = site;
      this.query   = query;
      this.dbFiles = config.getDBFile();
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

    public String getQuery() {
      return query;
    }

    public BlastQueryConfig getConfig() {
      return config;
    }
  }
}
