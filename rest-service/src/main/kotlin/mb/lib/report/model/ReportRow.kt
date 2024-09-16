package mb.lib.report.model

import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

open class ReportRow(
  val reportID: HashID,
  val jobID: HashID,
  var status: JobStatus,
  val config: BlastFormatter?,
  var queueID: Int,
  val createdOn: OffsetDateTime
) {

  @JsonValue
  fun toJson() = Json.new<ObjectNode> {
    put(JsonKeys.ReportID, reportID.string)
    put(JsonKeys.JobID, jobID.string)
    put(JsonKeys.Status, status.value)
    put(JsonKeys.Config, config?.toJSON()?.toJSON())
    put(JsonKeys.QueueID, queueID)
    put(JsonKeys.CreatedOn, createdOn.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
  }
}
