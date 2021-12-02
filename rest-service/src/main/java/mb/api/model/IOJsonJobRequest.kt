package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.blast.IOBlastConfig
import mb.api.model.io.JsonKeys

@JsonInclude(NON_NULL)
data class IOJsonJobRequest(
  @JsonProperty(JsonKeys.Site)
  val site: String,

  @JsonProperty(JsonKeys.Targets)
  val targets: Set<IOJobTarget>,

  @JsonProperty(JsonKeys.Config)
  val config: IOBlastConfig,

  @JsonProperty(JsonKeys.Description)
  val description: String?,

  @JsonProperty(JsonKeys.MaxResults)
  val maxResults: Int?,

  @JsonProperty(JsonKeys.MaxResultSize)
  val maxResultSize: Long?,

  @JsonProperty(JsonKeys.MaxSequences)
  val maxSequences: Byte?,

  @JsonProperty(JsonKeys.IsPrimary)
  val isPrimary: Boolean?,
)