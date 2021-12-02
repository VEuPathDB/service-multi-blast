package mb.api.model.internal

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonSetter
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.blast.BlastTool
import java.time.OffsetDateTime

@JsonInclude(NON_DEFAULT)
class Job(@get:JsonGetter(JsonKeys.Tool) val tool: BlastTool) {

  @get:JsonIgnore
  @set:JsonIgnore
  var createdOn: OffsetDateTime? = null

  @get:JsonGetter(JsonKeys.Config)
  @set:JsonSetter(JsonKeys.Config)
  var jobConfig: BlastConfig? = null

  @get:JsonGetter(JsonKeys.ProjectID)
  @set:JsonSetter(JsonKeys.ProjectID)
  var project: String? = null

  @get:JsonIgnore
  val hasConfig get() = jobConfig != null
}