package mb.lib.report

import mb.lib.config.Config
import mb.lib.queue.QueueManager
import mb.lib.queue.consts.URL
import mb.lib.report.model.ReportPayload
import mb.lib.report.model.Request
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.fireworq.FireworqQueue

object ReportQueueManager : QueueManager() {
  private val log = LogManager.getLogger(ReportQueueManager::class.java)

  override val fireworq =
    FireworqQueue(URL.prependHTTP(Config.queueHost), Config.formatQueueName)

  /**
   * Report endpoint path segment.  There should be no slashes in this value as
   * they are inserted when the full path is built.
   */
  private const val reportEndpoint = "report"

  fun submitNewJob(payload: ReportPayload): Int {
    log.trace("#submitNewJob(payload={})", payload)
    return submitNewJob(Config.formatQueueName, Request(
      java.lang.String.join("/",
        URL.prependHTTP(Config.formatterHost),
        reportEndpoint),
      payload
    ))
  }
}
