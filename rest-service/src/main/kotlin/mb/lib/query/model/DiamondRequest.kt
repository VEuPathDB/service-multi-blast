package mb.lib.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import mb.api.model.io.JsonKeys
import mb.lib.util.toBlastServerParams
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.lib.jackson.toJsonArray

data class DiamondRequest(
  @JsonProperty(JsonKeys.JobID)
  override val jobID:  HashID,

  @JsonProperty(JsonKeys.Tool)
  val tool:   DiamondCommand,

  @JsonProperty(JsonKeys.Params)
  val config: DiamondCommandConfig,
) : BlastServerRequest {
  @JsonValue
  fun toJSON() = Json.newObject {
    put(JsonKeys.JobID, jobID.string)
    put(JsonKeys.Tool, tool.command)
    put(JsonKeys.Params, config.toBlastServerParams().toJsonArray())
  }
}
