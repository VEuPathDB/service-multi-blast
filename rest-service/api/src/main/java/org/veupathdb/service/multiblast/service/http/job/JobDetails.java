package org.veupathdb.service.multiblast.service.http.job;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponse;
import org.veupathdb.service.multiblast.generated.model.IOJobPostResponseImpl;
import org.veupathdb.service.multiblast.model.internal.Job;
import org.veupathdb.service.multiblast.service.cli.CliBuilder;
import org.veupathdb.service.multiblast.util.Format;

public class JobDetails
{
  File       source;
  byte[]     hash;
  String     id;
  Job        job;
  CliBuilder cli;
  long       userID;
  String     description;
  long       maxDlSize;
  byte[]     parentHash;

  @Override
  public String toString() {
    return Format.Json.createObjectNode()
      .put("source", source.getName())
      .put("hash", id)
      .put("id", id)
      .put("job", job.toString())
      .put("cli", cli.toString())
      .put("userID", userID)
      .put("description", description)
      .put("maxDlSize", maxDlSize)
      .put("parentHash", parentHash)
      .toString();
  }
}
