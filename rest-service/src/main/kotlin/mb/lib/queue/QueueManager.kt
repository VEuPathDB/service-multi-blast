package mb.lib.queue

import mb.lib.queue.model.CreateRequest
import mb.lib.util.logger
import org.veupathdb.lib.fireworq.FireworqQueue

abstract class QueueManager {
  protected val logger = logger()

  protected abstract val fireworq: FireworqQueue

  fun getFailedJobs() = fireworq.getFailed()

  fun grabbedJobs() = fireworq.getGrabbed()

  fun queueSize() = fireworq.getWaiting().size + fireworq.getGrabbed().size

  fun submitNewJob(category: String, req: CreateRequest<*>): Int {
    logger.debug("submitting job {} to the queue") { req.toJSON().toString() }
    return fireworq.submitJob(category, req.toInternal()).jobID
  }
}
