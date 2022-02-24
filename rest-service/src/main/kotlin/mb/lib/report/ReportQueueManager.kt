package mb.lib.report

import io.prometheus.client.Gauge
import mb.lib.config.Config
import mb.lib.queue.QueueManager
import mb.lib.queue.consts.URL
import mb.lib.report.model.ReportPayload
import mb.lib.report.model.Request
import org.veupathdb.lib.fireworq.FireworqQueue

object ReportQueueManager : QueueManager() {

  private val queueSizeGauge = Gauge.build(
    "report_queue_size",
    "Number of jobs currently waiting in the blast queue."
  ).register()

  init {
    Thread {
      while (true) {
        queueSizeGauge.set(queueSize().toDouble())

        // Sleep 15 seconds
        Thread.sleep(15_000)
      }
    }.start()
  }

  override val fireworq =
    FireworqQueue(URL.prependHTTP(Config.queueHost), Config.formatQueueName)

  /**
   * Report endpoint path segment.  There should be no slashes in this value as
   * they are inserted when the full path is built.
   */
  private const val reportEndpoint = "report"

  fun submitNewJob(payload: ReportPayload): Int {
    return submitNewJob(Config.formatQueueName, Request(
      java.lang.String.join("/",
        URL.prependHTTP(Config.formatterHost),
        reportEndpoint),
      payload
    ))
  }
}
