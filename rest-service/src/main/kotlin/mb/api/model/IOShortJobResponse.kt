package mb.api.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus

@JsonInclude(NON_NULL)
open class IOShortJobResponse(
  @get:JsonGetter(JsonKeys.ID)
  val id: String,

  @get:JsonGetter(JsonKeys.Description)
  val description: String?,

  @get:JsonGetter(JsonKeys.Status)
  val status: JobStatus,

  @get:JsonGetter(JsonKeys.Created)
  val created: String?,

  @get:JsonGetter(JsonKeys.MaxResultSize)
  val maxResultSize: Long,

  @get:JsonGetter(JsonKeys.ParentJobs)
  val parentJobs: Array<IOJobLink>?,

  @get:JsonGetter(JsonKeys.ChildJobs)
  val childJobs: Array<IOJobLink>?,

  @get:JsonGetter(JsonKeys.IsPrimary)
  val isPrimary: Boolean,

  @get:JsonGetter(JsonKeys.Site)
  val site: String,

  @get:JsonGetter(JsonKeys.Targets)
  val targets: Array<IOJobTarget>?,

  @get:JsonGetter(JsonKeys.IsRerunnable)
  val isRerunnable: Boolean,
)
