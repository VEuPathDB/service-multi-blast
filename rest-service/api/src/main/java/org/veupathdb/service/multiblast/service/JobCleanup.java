package org.veupathdb.service.multiblast.service;

import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;

import mb.lib.config.Config;
import mb.lib.db.JobDBManager;
import mb.lib.extern.BlastQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.util.Format;

public class JobCleanup implements Runnable
{
  private static final Logger log = LogManager.getLogger(JobCleanup.class);
  private static final Config conf = Config.getInstance();

  @Override
  public void run() {
    try {
      runChecked();
    } catch (Exception e) {
      log.error("Job cleanup ended unexpectedly.", e);
    }
  }

  public void runChecked() throws Exception {
    log.trace("#runChecked()");
    log.info("Job Pruning: start");

    try (var db = new JobDBManager()) {
      var now = OffsetDateTime.now();

      deleteOldFiles(now);

      var jobs = db.getStaleJobs(now);

      log.info("Job Pruning: found " + jobs.size() + " stale jobs");

      for (var job : jobs) {
        var idString = Format.toHexString(job.jobHash());
        log.debug("deleting job {} db entries", idString);
        db.deleteJob(job.jobHash());

        log.debug("deleting job {} workspace", idString);
        JobDataManager.deleteJobData(idString);

        log.debug("deleting job {} queue entry", idString);
        BlastQueueManager.getInstance().deleteJob(job.queueID());

        job.close();
      }

      log.info("Job Pruning: deleting stale guest jobs");
      db.deleteStaleGuests();

      log.debug("Job Pruning: fetching orphaned jobs");
      var orphans = db.getOrphanedJobs();

      log.debug("Job Pruning: found {} orphan jobs.", orphans.size());
      for (var id : orphans) {
        db.deleteJobToJobLinks(id);
        db.deleteJobToTargetLinks(id);
        db.deleteJob(id);
      }

      log.debug("Job Pruning: clearing old failed jobs from job queue.");
      deleteOldFailedJobs(now);
    } catch (Exception ex) {
      log.error("Job Pruning: error while attempting to prune jobs: ", ex);
      throw ex;
    }

    log.info("Job Pruning: complete");
  }

  private void deleteOldFiles(OffsetDateTime now) throws Exception {
    var delPoint = now.minus(5, ChronoUnit.DAYS);

    var old = JobDataManager.getPathsCreatedBefore(delPoint);

    log.info("Job Pruning: pruning " + old.size() + " files");

    for (var file : old)
      Files.delete(file);
  }

  private void deleteOldFailedJobs(OffsetDateTime now) throws Exception {
    var failed = BlastQueueManager.getInstance().getFailedJobs();
    var delPoint = now.minus(conf.getJobTimeout(), ChronoUnit.DAYS);

    for (var job : failed)
      if (job.getCreatedAt().isBefore(delPoint))
        BlastQueueManager.getInstance().deleteJobFailure(job);
  }
}
