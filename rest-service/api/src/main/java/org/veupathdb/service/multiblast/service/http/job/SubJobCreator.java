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

class SubJobCreator
{
  static void createJobs(JobDetails[] jobs) throws Exception {
    for (var jd : jobs) {
      createJob(jd);
    }
  }

  static void createJob(JobDetails job) throws Exception {
    var jobPath = JobDataManager.createJobWorkspace(job.id);
    var queueID = JobQueueManager.submitJob(
      job.id,
      job.job.getTool().value(),
      job.cli.toArgArray(false)
    );
    var now   = OffsetDateTime.now();
    var moved = Files.copy(job.source.toPath(), jobPath.resolve("query.txt"));

    JobDBManager.registerJob(
      new FullJobRowImpl(
        job.hash,
        queueID,
        now,
        now.plusDays(Config.getInstance().getJobTimeout()),
        job.job.toSerial(),
        Files.readString(moved)
      ),
      new UserRowImpl(job.hash, job.userID, job.description, job.parentHash, job.maxDlSize)
    );
  }
}

class JobDetails
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
}
