package mb.lib.query

import mb.api.service.util.Address
import mb.lib.config.Config
import mb.lib.model.EmptyBlastConfig
import mb.lib.model.HashID
import mb.lib.model.JobStatus
import mb.lib.query.model.BlastRequest
import mb.lib.query.model.FailedQueryJob
import mb.lib.query.model.FailedQueryJobResponse
import mb.lib.queue.QueueManager
import mb.lib.queue.consts.URL
import mb.lib.queue.model.CreateRequest
import mb.lib.queue.model.FailedJobResponse
import mb.lib.util.parseJSON
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.blast.BlastConfig
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.reflect.KClass

object BlastQueueManager: QueueManager<FailedQueryJob>()
{
  private val log  = LogManager.getLogger(BlastQueueManager::class.java)

  private const val Path = "blast"

  override val submissionURL
    get() = URL.jobSubmissionEndpoint(Config.blastJobCategory)

  override val failedJobs: List<FailedQueryJob>
    get() {
      log.trace("#getFailedJobs()")
      return getFailedJobs(Config.blastQueueName).failedJobs
    }

  /**
   * Retrieves and returns the status for a given job ID in the blast job queue.
   *
   * @param jobID External ID of the job to check.
   *
   * @return Status of the checked job.
   */
  override fun getJobStatus(jobID: Int): JobStatus {
    log.trace("#getJobStatus(jobID={})", jobID)
    return getJobStatus(Config.blastQueueName, jobID)
  }

  // ------------------------------------------------------------------------------------------ //

  override fun jobInFailList(jobID: Int): Boolean {
    log.trace("#jobInFailList(jobID={})", jobID)
    return jobInFailList(Config.blastQueueName, jobID)
  }

  override fun deleteJobFailure(failID: Int) {
    log.trace("#deleteJobFailure(failID={})", failID)
    deleteJobFailure(Config.blastQueueName, failID)
  }

  override fun deleteJob(jobID: Int) {
    log.trace("#deleteJob(jobID={})", jobID)
    deleteJob(Config.blastQueueName, jobID)
  }

  override fun getFailedJobs(queue: String): FailedQueryJobResponse {
    return HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedEndpoint(queue)).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    ).body().parseJSON()
  }

  /**
   * Submits a new Blast job to the job queue.
   *
   * @param jobId  Job configuration digest (used as a unique identifier)
   * @param config Blast tool CLI parameters (starting with the blast tool
   *               itself).
   *
   * @return the queue ID for the queued job
   */
  fun submitNewJob(jobId: HashID, config: BlastConfig): Int {
    log.trace("#submitJob(jobID={}, config={})", jobId, config)

    if (config is EmptyBlastConfig)
      throw RuntimeException("Invalid config cannot be submitted.")

    return submitNewJob(
      Config.blastQueueName,
      CreateRequest(
        java.lang.String.join("/", Address.http(Config.blastHost), Path),
        BlastRequest(jobId, config.tool, config)
      )
    )
  }
}
