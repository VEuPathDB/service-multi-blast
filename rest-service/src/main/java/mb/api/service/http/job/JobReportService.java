package mb.api.service.http.job;

import mb.lib.db.JobDBManager;
import mb.lib.db.model.FullJobRow;
import mb.lib.querier.BlastQueueManager;
import mb.lib.queue.model.QueueJobStatus;
import mb.lib.blast.model.BlastReportType;
import mb.lib.data.JobDataManager;
import mb.api.model.internal.Job;
import mb.api.service.cli.CliBuilder;

public class JobReportService
{
  /**
   * Ensures the job is cached by first checking to see if it exists, and if it
   * does not, synchronously re-runs the job to populate the cache.
   *
   * @param job    Job to check.
   * @param userID ID of the user requesting the cache check.
   */
  public static void ensureJobCache(FullJobRow job, long userID) throws Exception {
    if (JobUtil.jobIsCached(job))
      return;

    var dets = new JobDetails();
    dets.id    = job.jobID();
    dets.query = job.query();
    dets.job   = Job.fromSerial(job.config());
    dets.cli    = new CliBuilder();
    dets.userID = userID;

    dets.job.getJobConfig().toCli(dets.cli);

    int queueID;
    try(var db = new JobDBManager()) {
      queueID = new JobCreator(db).handleRerun(dets);
    }

    // Wait for job to complete
    QueueJobStatus stat;
    for (
      stat = BlastQueueManager.jobStatus(queueID);
      stat == QueueJobStatus.Queued || stat == QueueJobStatus.InProgress;
      stat = BlastQueueManager.jobStatus(queueID)
    ) {
      Thread.sleep(250);
    }

    if (stat == QueueJobStatus.Errored) {
      throw new Exception(JobDataManager.jobWorkspace(dets.id).errorText());
    }
  }

  public static BlastReportType parseFormatString(String format) {
    try {
      return BlastReportType.fromID(Integer.parseInt(format));
    } catch (NumberFormatException ignored) {
    }

    return BlastReportType.fromName(format);
  }
}
