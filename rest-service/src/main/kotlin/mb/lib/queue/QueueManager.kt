package mb.lib.queue

import mb.lib.model.JobStatus
import mb.lib.queue.model.CreateRequest
import org.veupathdb.lib.fireworq.FireworqQueue

abstract class QueueManager
{
  protected abstract val fireworq: FireworqQueue


  fun getFailedJobs() = fireworq.getFailed()

  fun getJobStatus(jobID: Int): JobStatus {
    val job = fireworq.getJob(jobID) ?:
      return if (jobInFailList(jobID))
        JobStatus.Errored
      else
        JobStatus.Completed

    return JobStatus.unsafeFromString(job.status)
  }

  fun grabbedJobs() = fireworq.getGrabbed()

  fun queueSize() = fireworq.getWaiting().size + fireworq.getGrabbed().size

  fun jobInFailList(jobID: Int) = fireworq.jobInFailList(jobID)

  fun deleteJobFailure(failID: Int) {
    fireworq.deleteJobFailure(failID)
  }

  fun submitNewJob(category: String, req: CreateRequest<*>) =
    fireworq.submitJob(category, req.toInternal()).jobID
}
