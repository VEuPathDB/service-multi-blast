package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.model.blast.IOBlastConfig
import mb.api.model.io.JsonKeys
import mb.lib.model.JobStatus

@JsonInclude(NON_NULL)
class IOLongJobResponse(
  id: String? = null,
  description: String? = null,
  status: JobStatus? = null,
  created: String? = null,
  expires: String? = null,
  maxResultSize: Long? = null,
  parentJobs: Array<IOJobLink>? = null,
  childJobs: Array<IOJobLink>? = null,
  isPrimary: Boolean,
  site: String? = null,
  targets: Array<IOJobTarget>? = null,
  @JsonProperty(JsonKeys.Config) val config: IOBlastConfig
) : IOShortJobResponse(
  id, description, status, created, expires, maxResultSize, parentJobs,
  childJobs, isPrimary, site, targets
)