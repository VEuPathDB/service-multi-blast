package mb.lib.report.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonSetter
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.hash_id.HashID

@JsonInclude(JsonInclude.Include.NON_EMPTY)
data class ReportPayload(
  @get:JsonGetter(JsonKeys.JobID)
  @set:JsonSetter(JsonKeys.JobID)
  var jobID: HashID? = null,

  @get:JsonGetter(JsonKeys.ReportID)
  @set:JsonSetter(JsonKeys.ReportID)
  var reportID: HashID? = null,

  @get:JsonGetter(JsonKeys.Config)
  @set:JsonSetter(JsonKeys.Config)
  var config: BlastFormatter? = null
)