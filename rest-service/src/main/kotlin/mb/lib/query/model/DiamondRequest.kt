package mb.lib.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.lib.jackson.put

data class DiamondRequest(
  @JsonProperty(JsonKeys.JobID)
  val jobID:  HashID,

  @JsonProperty(JsonKeys.Tool)
  val tool:   DiamondCommand,

  @JsonProperty(JsonKeys.Config)
  val config: DiamondCommandConfig,
) {
  @JsonValue
  fun toJSON() = Json.newObject {
    put(JsonKeys.JobID, jobID.string)
    put(JsonKeys.Tool, tool.command)
    put(JsonKeys.Config, config)
  }
}
