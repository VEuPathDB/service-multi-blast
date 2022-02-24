package mb.lib.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import mb.lib.model.HashID
import mb.lib.util.jsonObject
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.blast.BlastTool

data class BlastRequest(
  @JsonProperty(JsonKeys.JobID)  val jobID:  HashID,
  @JsonProperty(JsonKeys.Tool)   val tool:   BlastTool,
  @JsonProperty(JsonKeys.Config) val config: BlastConfig,
) {
  @JsonValue
  fun toJSON() = jsonObject {
    set<ObjectNode>(JsonKeys.JobID, jobID.toJSON())
    set<ObjectNode>(JsonKeys.Tool, tool.toJSON())
    set<ObjectNode>(JsonKeys.Config, config.toJSON().toJSON())!!
  }
}
