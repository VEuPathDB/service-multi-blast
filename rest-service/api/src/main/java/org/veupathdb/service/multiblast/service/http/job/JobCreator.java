package org.veupathdb.service.multiblast.service.http.job;

import java.nio.file.Files;
import java.sql.Connection;
import java.time.OffsetDateTime;
import java.util.Arrays;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.JobLink;
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

  private final Connection con;

  public JobCreator(Connection con) {
    this.con = con;
  }

  public void handleLink(JobDetails d) throws Exception {
    log.trace("#handleLink(d={})", d);

    var exp = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());

    JobDBManager.updateJobDeleteTimer(con, d.hash, exp);

    if (!JobDBManager.userIsLinkedToJob(con, d.userID, d.hash))
      JobDBManager.linkUserToJob(con, new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize, d.isPrimary));
    else
      JobDBManager.updateLinkIsPrimary(con, d.userID, d.hash);
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

    JobDBManager.updateJobDeleteTimer(con, d.hash, exp);
    JobDBManager.updateJobQueueID(con, d.hash, queId);
    JobDBManager.updateJobStatus(con, d.hash, DBJobStatus.Queued);

    if (!JobDBManager.userIsLinkedToJob(con, d.userID, d.hash))
      JobDBManager.linkUserToJob(con, new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize, d.isPrimary));

    if (d.parentHash != null) {
      var noLinks = JobDBManager.getJobLinks(con, d.parentHash).stream()
        .map(JobLink::jobHash)
        .noneMatch(h -> Arrays.equals(d.hash, h));
      if (noLinks)
        JobDBManager.createJobLink(con, d.hash, d.parentHash);
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

    JobDBManager.registerJob(
      con,
      new FullJobRowImpl(d.hash, queId, now, exp, d.job.toSerial(), qFile.toFile(), d.projectID, DBJobStatus.Queued),
      new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize, d.isPrimary),
      Arrays.asList(d.targets)
    );

    if (d.parentHash != null) {
      var noLinks = JobDBManager.getJobLinks(con, d.parentHash).stream()
        .map(JobLink::jobHash)
        .noneMatch(h -> Arrays.equals(d.hash, h));
      if (noLinks)
        JobDBManager.createJobLink(con, d.hash, d.parentHash);
    }
  }
}
