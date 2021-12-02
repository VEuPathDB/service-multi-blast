package mb.api.model.reports

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.*
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys
import mb.lib.model.HashID
import mb.lib.model.JobStatus

@JsonInclude(NON_EMPTY)
data class ReportResponse(
  @JsonProperty(JsonKeys.JobID)       val jobID:       HashID,
  @JsonProperty(JsonKeys.ReportID)    val reportID:    HashID,
  @JsonProperty(JsonKeys.Config)      val config:      ReportRequest,
  @JsonProperty(JsonKeys.Status)      val status:      JobStatus,
  @JsonProperty(JsonKeys.Description) val description: String? = null,
) {
  @JsonProperty(JsonKeys.Files)
  val files: MutableList<String> = ArrayList()
}