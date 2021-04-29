package mb.api.service.http.job;

import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.util.Arrays;

import mb.lib.config.Config;
import mb.lib.data.JobDataManager;
import mb.lib.db.JobDBManager;
import mb.lib.db.model.DBJobStatus;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.db.model.impl.UserRowImpl;
import mb.lib.model.JobStatus;
import mb.lib.querier.BlastQueueManager;
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

    db.updateJobDeleteTimer(d.id, exp);

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.id))
      db.createJobLink(d.id, d.parentHash);

    if (!db.userIsLinkedToJob(d.userID, d.id))
      db.linkUserToJob(new UserRowImpl(d.id, d.userID, d.description, d.maxDlSize, d.isPrimary));
    else if (d.parentHash == null)
      db.updateLinkIsPrimary(d.userID, d.id);
  }

  public int handleRerun(JobDetails d) throws Exception {
    log.trace("#handleRerun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteWorkspace(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    Files.move(d.query.toPath(), jobDir.filePath("query.txt"));
    var exp    = OffsetDateTime.now().plusDays(Config.getInstance().getJobTimeout());
    var queId  = BlastQueueManager.submitJob(d.id, d.job.getTool(), d.cli.toArgArray(false));

    db.updateJobDeleteTimer(d.id, exp);
    db.updateJobQueueID(d.id, queId);
    db.updateJobStatus(d.id, JobStatus.Queued);

    if (!db.userIsLinkedToJob(d.userID, d.id))
      db.linkUserToJob(new UserRowImpl(
        d.id,
        d.userID,
        d.description,
        d.maxDlSize,
        d.isPrimary
      ));

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.id)) {
      db.createJobLink(d.id, d.parentHash);
    }

    return queId;
  }

  public void handleInitialRun(JobDetails d) throws Exception {
    log.trace("#handleInitialRun(d={})", d);

    // Clean up just to be safe.  If the service was reset, there may be orphan
    // workspaces.
    JobDataManager.deleteWorkspace(d.id);

    var jobDir = JobDataManager.createJobWorkspace(d.id);
    var qFile  = Files.move(d.query.toPath(), jobDir.filePath("query.txt"));
    var now    = OffsetDateTime.now();
    var exp    = now.plusDays(Config.getInstance().getJobTimeout());
    var queId  = BlastQueueManager.submitJob(d.id, d.job.getTool(), d.cli.toArgArray(false));

    db.registerJob(
      new FullJobRowImpl(
        d.id,
        queId,
        now,
        exp,
        d.job.toSerial(),
        qFile.toFile(),
        d.projectID,
        DBJobStatus.Queued
      ),
      new UserRowImpl(d.id, d.userID, d.description, d.maxDlSize, d.isPrimary),
      Arrays.asList(d.targets)
    );

    if (d.parentHash != null && !db.jobToJobLinkExists(d.parentHash, d.id)) {
      db.createJobLink(d.id, d.parentHash);
    }
  }
}
