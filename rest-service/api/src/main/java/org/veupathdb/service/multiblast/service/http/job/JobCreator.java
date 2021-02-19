package org.veupathdb.service.multiblast.service.http.job;

import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Arrays;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Should job-to-job links be per user?
// No. Subjobs will only exist on multi-query parents and those links are
// universal.  They say the parent job contains the sub-jobs.
public class JobCreator
{
  private final Logger log;

  public JobCreator() {
    this.log = LogManager.getLogger(getClass());
  }

  public void handleLink(JobDetails d) throws Exception {
    log.trace("#handleLink(d={})", d);

    var exp = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());

    JobDBManager.updateJobDeleteTimer(d.hash, exp);

    if (!JobDBManager.userIsLinkedToJob(d.userID, d.hash))
      JobDBManager.linkUserToJob(new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize));
  }

  public void handleRerun(JobDetails d) throws Exception {
    log.trace("#handleRerun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteJobData(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    Files.move(d.source.toPath(), jobDir.resolve("query.txt"));
    var exp    = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());
    var queId  = JobQueueManager.submitJob(d.id, d.job.getTool().value(), d.cli.toArgArray(false));

    JobDBManager.updateJobDeleteTimer(d.hash, exp);
    JobDBManager.updateJobQueueID(d.hash, queId);

    if (!JobDBManager.userIsLinkedToJob(d.userID, d.hash))
      JobDBManager.linkUserToJob(new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize));

    if (d.parentHash != null) {
      var noLinks = JobDBManager.getJobLinks(d.parentHash).stream()
        .map(JobLink::jobHash)
        .noneMatch(h -> Arrays.equals(d.hash, h));
      if (noLinks)
        JobDBManager.createJobLink(d.hash, d.parentHash);
    }
  }

  public void handleInitialRun(JobDetails d) throws Exception {
    log.trace("#handleInitialRun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteJobData(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    var qFile  = Files.move(d.source.toPath(), jobDir.resolve("query.txt"));
    var now    = OffsetDateTime.now();
    var exp    = now.plusDays(Config.getInstance().getJobTimeout());
    var queId  = JobQueueManager.submitJob(d.id, d.job.getTool().value(), d.cli.toArgArray(false));

    JobDBManager.registerJob(
      new FullJobRowImpl(d.hash, queId, now, exp, d.job.toSerial(), qFile.toFile()),
      new UserRowImpl(d.hash, d.userID, d.description, d.maxDlSize)
    );

    if (d.parentHash != null) {
      var noLinks = JobDBManager.getJobLinks(d.parentHash).stream()
        .map(JobLink::jobHash)
        .noneMatch(h -> Arrays.equals(d.hash, h));
      if (noLinks)
        JobDBManager.createJobLink(d.hash, d.parentHash);
    }
  }
}
