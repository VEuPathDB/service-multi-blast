package mb.lib.query

import mb.api.service.util.Address
import mb.lib.config.Config
import mb.lib.model.EmptyBlastConfig
import mb.lib.model.HashID
import mb.lib.query.model.BlastRequest
import mb.lib.queue.QueueManager
import mb.lib.queue.consts.URL
import mb.lib.queue.model.CreateRequest
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.fireworq.FireworqQueue

object BlastQueueManager: QueueManager()
{
  private const val Path = "blast"

  override val fireworq: FireworqQueue =
    FireworqQueue(URL.prependHTTP(Config.queueHost), Config.blastQueueName)

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
