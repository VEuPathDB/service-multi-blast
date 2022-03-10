package mb.api.model.reports

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

data class ReportResponse(
  @JsonProperty(JsonKeys.JobID)       val jobID:       HashID,
  @JsonProperty(JsonKeys.ReportID)    val reportID:    HashID,
  @JsonProperty(JsonKeys.Config)      val config:      ReportRequest,
  @JsonProperty(JsonKeys.Status)      val status:      JobStatus,
  @JsonProperty(JsonKeys.Description) val description: String? = null,
) {
  @JsonProperty(JsonKeys.Files)
  val files: MutableList<String> = ArrayList()

  @JsonValue
  fun toJson(): ObjectNode =
    Json.new {
      put(JsonKeys.JobID, jobID.string)
      put(JsonKeys.ReportID, reportID.string)
      set<ObjectNode>(JsonKeys.Config, config.toJson())
      put(JsonKeys.Status, status.value)
      description?.let { put(JsonKeys.Description, it) }

      if (files.isNotEmpty())
        putPOJO(JsonKeys.Files, files)
    }
}