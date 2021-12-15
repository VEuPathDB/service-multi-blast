package mb.lib.queue

import mb.lib.model.JobStatus
import mb.lib.queue.consts.URL
import mb.lib.queue.model.*
import mb.lib.util.jsonStringify
import mb.lib.util.parseJSON
import org.apache.logging.log4j.LogManager
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.reflect.KClass

abstract class QueueManager<T: FailedJob<*>>
{
  companion object {
    private val log = LogManager.getLogger(QueueManager::class.java)
  }

  abstract val failedJobs: List<T>

  /**
   * @return The URL to use when submitting jobs to the managed queue.
   */
  abstract val submissionURL: URI

  abstract fun getJobStatus(jobID: Int): JobStatus

  abstract fun jobInFailList(jobID: Int): Boolean

  abstract fun deleteJobFailure(failID: Int)

  abstract fun deleteJob(jobID: Int)

  abstract fun getFailedJobs(queue: String): FailedJobResponse<T>

  fun getJobStatus(queueName: String, jobID: Int): JobStatus {
    log.trace("#getJobStatus(queueName={}, jobID={})", queueName, jobID)

    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(queueName, jobID)).GET().build(),
      HttpResponse.BodyHandlers.ofString()
    )

    return when (res.statusCode()) {
      404  -> if (jobInFailList(jobID)) JobStatus.Errored else JobStatus.Completed
      200  -> res.body().parseJSON<JobStatusResponse>().status
      else -> {
        log.error(
          "Queue server returned an unexpected response.  Code: {}.  Output: {}",
          res.statusCode(),
          res.body()
        )

        throw Exception("Queue server returned an unexpected response.")
      }
    }
  }

  fun jobInFailList(queue: String, jobID: Int): Boolean {
    log.trace("#jobInFailList(queueName={}, jobID={})", queue, jobID)

    for (job in getFailedJobs(queue).failedJobs) {
      if (job.jobID == jobID) {
        return true
      }
    }

    return false
}

  fun deleteJobFailure(queueName: String, failID: Int) {
    log.trace("#deleteJobFailure(queueName={}, failID={})", queueName, failID)

    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().
        uri(URL.failedIDEndpoint(queueName, failID)).
        DELETE().
        build(),
      HttpResponse.BodyHandlers.discarding()
    )
  }

  fun submitNewJob(queueName: String, req: CreateRequest<*>): Int {
    log.trace("#submitNewJob(queueName={}, req={})", queueName, req)

    val res = HttpClient.newHttpClient().send(
      HttpRequest.newBuilder()
        .uri(submissionURL)
        .POST(HttpRequest.BodyPublishers.ofString(req.jsonStringify()))
        .build(),
      HttpResponse.BodyHandlers.ofString()
    )

    if (res.statusCode() != 200) {
      log.error(
        "Queue server returned an unexpected response.  Code: {}.  Output: {}",
        res.statusCode(),
        res.body()
      )
      throw RuntimeException("Queue server returned an unexpected response.")
    }

    return res.body().parseJSON<JobCreateResponse>().id
  }

  fun deleteJob(name: String, jobID: Int) {
    log.trace("#deleteJob(queueName={}, jobID={})", name, jobID)
    HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.queueJobEndpoint(name, jobID)).DELETE().build(),
      HttpResponse.BodyHandlers.discarding()
    )
  }
}
