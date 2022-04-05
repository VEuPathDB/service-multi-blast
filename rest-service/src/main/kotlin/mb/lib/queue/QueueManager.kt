package mb.lib.queue

import mb.lib.model.JobStatus
import mb.lib.queue.model.CreateRequest
import org.veupathdb.lib.fireworq.FireworqQueue

abstract class QueueManager
{
  protected abstract val fireworq: FireworqQueue


  fun getFailedJobs() = fireworq.getFailed()

  fun grabbedJobs() = fireworq.getGrabbed()

  fun queueSize() = fireworq.getWaiting().size + fireworq.getGrabbed().size

  fun submitNewJob(category: String, req: CreateRequest<*>) =
    fireworq.submitJob(category, req.toInternal()).jobID
}
