package mb.lib.query.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.hash_id.HashID


@JsonDeserialize(using = BlastServerRequestDeserializer::class)
interface BlastServerRequest {
  val jobID: HashID
}

internal fun BlastServerRequest(jobID: HashID, config: JobConfig): BlastServerRequest =
  when (config) {
    is mb.lib.query.model.BlastConfig -> BlastServerRequest(jobID, config.config)
    is DiamondConfig -> BlastServerRequest(jobID, config.config)
  }

internal fun BlastServerRequest(jobID: HashID, config: BlastConfig): BlastServerRequest =
  BlastRequest(jobID, config.tool, config)

internal fun BlastServerRequest(jobID: HashID, config: DiamondCommandConfig): BlastServerRequest =
  DiamondRequest(jobID, config.tool, config)
