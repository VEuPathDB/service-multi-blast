package org.veupathdb.service.multiblast.service.http.job;

import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Arrays;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.DBJobStatus;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JobCreator
{
  private static final Logger log = LogManager.getLogger(JobCreator.class);

  private final JobDBManager db;

  public JobCreator(JobDBManager db) {
    this.db = db;
  }

  public void handleLink(JobDetails d) throws Exception {
    log.trace("#handleLink(d={})", d);

    var exp = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());

    db.updateJobDeleteTimer(d.hash, exp);

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.hash))
      db.createJobLink(d.hash, d.parentHash);

    if (!db.userIsLinkedToJob(d.userID, d.hash))
      db.linkUserToJob(new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize, d.isPrimary));
    else if (d.parentHash == null)
      db.updateLinkIsPrimary(d.userID, d.hash);
  }

  public int handleRerun(JobDetails d) throws Exception {
    log.trace("#handleRerun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteJobData(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    Files.move(d.query.toPath(), jobDir.resolve("query.txt"));
    var exp    = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());
    var queId  = JobQueueManager.submitJob(d.id, d.job.getTool().value(), d.cli.toArgArray(false));

    db.updateJobDeleteTimer(d.hash, exp);
    db.updateJobQueueID(d.hash, queId);
    db.updateJobStatus(d.hash, DBJobStatus.Queued);

    if (!db.userIsLinkedToJob(d.userID, d.hash))
      db.linkUserToJob(new UserRowImpl(
        d.hash,
        d.userID,
        d.description,
        d.maxDlSize,
        d.isPrimary
      ));

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.hash)) {
      db.createJobLink(d.hash, d.parentHash);
    }

    return queId;
  }

  public void handleInitialRun(JobDetails d) throws Exception {
    log.trace("#handleInitialRun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteJobData(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    var qFile  = Files.move(d.query.toPath(), jobDir.resolve("query.txt"));
    var now    = OffsetDateTime.now();
    var exp    = now.plusDays(Config.getInstance().getJobTimeout());
    var queId  = JobQueueManager.submitJob(d.id, d.job.getTool().value(), d.cli.toArgArray(false));

    db.registerJob(
      new FullJobRowImpl(
        d.hash,
        queId,
        now,
        exp,
        d.job.toSerial(),
        qFile.toFile(),
        d.projectID,
        DBJobStatus.Queued
      ),
      new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize, d.isPrimary),
      Arrays.asList(d.targets)
    );

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.hash)) {
      db.createJobLink(d.hash, d.parentHash);
    }
  }
}
