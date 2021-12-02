package mb.lib.report

import mb.lib.config.Config
import mb.lib.model.JobStatus
import mb.lib.queue.QueueManager
import mb.lib.queue.consts.URL
import mb.lib.queue.model.FailedJobResponse
import mb.lib.report.model.FailedReportJob
import mb.lib.report.model.FailedReportJobResponse
import mb.lib.report.model.ReportPayload
import mb.lib.report.model.Request
import mb.lib.util.parseJSON
import org.apache.logging.log4j.LogManager
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

object ReportQueueManager: QueueManager<FailedReportJob>()
{
  private val log  = LogManager.getLogger(ReportQueueManager::class.java)

  /**
   * Report endpoint path segment.  There should be no slashes in this value as
   * they are inserted when the full path is built.
   */
  private const val reportEndpoint = "report"

  override val submissionURL
    get() = URL.jobSubmissionEndpoint(Config.formatJobCategory)

  /**
   * Retrieves and returns the status for a given job ID in the blast formatter
   * job queue.
   *
   * @param jobID ID of the job to check.
   *
   * @return Status of the checked job.
   */
  override fun getJobStatus(jobID: Int): JobStatus {
    log.trace("#getJobStatus(jobID={})", jobID)
    return getJobStatus(Config.formatQueueName, jobID)
  }

  /**
   * Retrieves and returns a list of failed jobs from the blast formatter job
   * queue.
   *
   * @return A list of zero or more failed jobs.
   */
  override val failedJobs: List<FailedReportJob>
    get() {
      log.trace("#getFailedJobs()")
      return getFailedJobs(Config.formatQueueName).failedJobs
    }
  override fun jobInFailList(jobID: Int): Boolean {
    log.trace("#jobInFailList(jobID={})", jobID)
    return jobInFailList(Config.formatQueueName, jobID)
  }

  override fun deleteJobFailure(failID: Int) {
    log.trace("#deleteJobFailure(failID={})", failID)
    deleteJobFailure(Config.formatQueueName, failID)
  }

  override fun deleteJob(jobID: Int) {
    log.trace("#deleteJob(jobID={})", jobID)
    deleteJob(Config.formatQueueName, jobID)
  }

  override fun getFailedJobs(queue: String): FailedReportJobResponse {
    return HttpClient.newHttpClient().send(
      HttpRequest.newBuilder().uri(URL.failedEndpoint(queue)).GET().build(),
      HttpResponse.BodyHandlers.ofInputStream()
    ).body().parseJSON()
  }

  fun submitNewJob(payload: ReportPayload): Int {
    log.trace("#submitNewJob(payload={})", payload)
    return submitNewJob(Config.formatQueueName, Request(
      java.lang.String.join("/", URL.prependHTTP(Config.formatterHost), reportEndpoint),
      payload
    ))
  }
}
