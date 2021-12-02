package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus

@JsonInclude(NON_NULL)
open class IOShortJobResponse(
  @JsonProperty(JsonKeys.ID)
  val id: String? = null,

  @JsonProperty(JsonKeys.Description)
  val description: String? = null,

  @JsonProperty(JsonKeys.Status)
  val status: JobStatus? = null,

  @JsonProperty(JsonKeys.Created)
  val created: String? = null,

  @JsonProperty(JsonKeys.Expires)
  val expires: String? = null,

  @JsonProperty(JsonKeys.MaxResultSize)
  val maxResultSize: Long? = null,

  @JsonProperty(JsonKeys.ParentJobs)
  val parentJobs: Array<IOJobLink>? = null,

  @JsonProperty(JsonKeys.ChildJobs)
  val childJobs: Array<IOJobLink>? = null,

  @JsonProperty(JsonKeys.IsPrimary)
  val isPrimary: Boolean,

  @JsonProperty(JsonKeys.Site)
  val site: String? = null,

  @JsonProperty(JsonKeys.Targets)
  val targets: Array<IOJobTarget>? = null,
)