package org.veupathdb.service.multiblast.service.http.job;

import java.io.File;

import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.util.Format;

public class JobDetails
{
  File       query;
  byte[]     hash;
  String     id;
  Job        job;
  CliBuilder cli;
  long       userID;
  String     description;
  Long       maxDlSize;
  byte[]     parentHash;
  boolean    isPrimary;

  @Override
  public String toString() {
    return Format.Json.createObjectNode()
      .put("query", query.getName())
      .put("hash", id)
      .put("id", id)
      .put("job", job.toString())
      .put("cli", cli.toString())
      .put("userID", userID)
      .put("description", description)
      .put("maxDlSize", maxDlSize)
      .put("parentHash", parentHash)
      .put("isPrimary", isPrimary)
      .toString();
  }
}
