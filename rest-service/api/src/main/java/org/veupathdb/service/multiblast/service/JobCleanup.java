package org.veupathdb.service.multiblast.service;

import java.time.OffsetDateTime;

import mb.lib.db.JobDBManager;
import mb.lib.extern.JobQueueManager;
import mb.lib.jobData.JobDataManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.service.multiblast.util.Format;

public class JobCleanup implements Runnable
{
  private static final Logger log = LogManager.getLogger(JobCleanup.class);

  @Override
  public void run() {
    try {
      runChecked();
    } catch (Exception ignored) {}
  }

  public void runChecked() throws Exception {
    log.trace("JobCleanup.run()");
    log.info("Job Pruning: start");
    try {
      var now = OffsetDateTime.now();
      var jobs = JobDBManager.getStaleJobs(now);

      log.info("Job Pruning: found " + jobs.size() + " stale jobs");

      for (var job : jobs) {
        var idString = Format.toHexString(job.jobHash());
        log.debug("deleting job {} from the database", idString);
        JobDBManager.deleteJob(job.jobHash());

        log.debug("deleting job {} workspace", idString);
        JobDataManager.deleteJobData(idString);

        log.debug("deleting job {} queue entry", idString);
        JobQueueManager.deleteJob(job.queueID());
      }
    } catch (Exception ex) {
      log.error("Job Pruning: error while attempting to prune jobs: ", ex);
      throw ex;
    }
    log.info("Job Pruning: complete");
  }
}