package org.veupathdb.service.multiblast.service;

import java.nio.file.Files;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.concurrent.TimeUnit;

import mb.lib.db.JobDBManager;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.util.Format;

import static java.time.temporal.ChronoUnit.HOURS;

public class JobCleanup implements Runnable
{
  private static final Logger log = LogManager.getLogger(JobCleanup.class);

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

    try {
      var now = OffsetDateTime.now();

      deleteOldFiles(now);

      var jobs = JobDBManager.getStaleJobs(now);

      log.info("Job Pruning: found " + jobs.size() + " stale jobs");

      for (var job : jobs) {
        var idString = Format.toHexString(job.jobHash());
        log.debug("deleting job {} db entries", idString);
        JobDBManager.deleteJob(job.jobHash());

        log.debug("deleting job {} workspace", idString);
        JobDataManager.deleteJobData(idString);

        log.debug("deleting job {} queue entry", idString);
        JobQueueManager.deleteJob(job.queueID());
      }

      log.info("Job Pruning: deleting stale guest jobs");
      JobDBManager.deleteStaleGuests();

      log.debug("Job Pruning: deleting orphan jobs");
      JobDBManager.deleteOrphanJobs();
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
}
