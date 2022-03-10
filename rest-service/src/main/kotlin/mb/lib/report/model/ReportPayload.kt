package mb.lib.report.model

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

data class ReportPayload(
  @set:JsonSetter(JsonKeys.JobID)    var jobID:    HashID?         = null,
  @set:JsonSetter(JsonKeys.ReportID) var reportID: HashID?         = null,
  @set:JsonSetter(JsonKeys.Config)   var config:   BlastFormatter? = null,
) {

  @JsonValue
  fun toJson() = Json.new<ObjectNode> {
    jobID?.run { put(JsonKeys.JobID, string) }
    reportID?.run { put(JsonKeys.ReportID, string) }
    config?.run { set<ObjectNode>(JsonKeys.Config, toJSON().toJSON()) }
  }
}