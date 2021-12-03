package mb.api.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus

@JsonInclude(NON_NULL)
open class IOShortJobResponse(
  @get:JsonGetter(JsonKeys.ID)
  val id: String? = null,

  @get:JsonGetter(JsonKeys.Description)
  val description: String? = null,

  @get:JsonGetter(JsonKeys.Status)
  val status: JobStatus? = null,

  @get:JsonGetter(JsonKeys.Created)
  val created: String? = null,

  @get:JsonGetter(JsonKeys.Expires)
  val expires: String? = null,

  @get:JsonGetter(JsonKeys.MaxResultSize)
  val maxResultSize: Long? = null,

  @get:JsonGetter(JsonKeys.ParentJobs)
  val parentJobs: Array<IOJobLink>? = null,

  @get:JsonGetter(JsonKeys.ChildJobs)
  val childJobs: Array<IOJobLink>? = null,

  @get:JsonGetter(JsonKeys.IsPrimary)
  val isPrimary: Boolean,

  @get:JsonGetter(JsonKeys.Site)
  val site: String? = null,

  @get:JsonGetter(JsonKeys.Targets)
  val targets: Array<IOJobTarget>? = null,
)