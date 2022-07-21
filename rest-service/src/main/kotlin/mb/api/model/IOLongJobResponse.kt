package mb.api.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import mb.api.model.blast.IOBlastConfig
import mb.lib.model.JobStatus

@JsonInclude(NON_NULL)
class IOLongJobResponse(
  id:            String,
  description:   String?,
  status:        JobStatus,
  created:       String?,
  maxResultSize: Long,
  parentJobs:    Array<IOJobLink>?,
  childJobs:     Array<IOJobLink>?,
  isPrimary:     Boolean,
  site:          String,
  targets:       Array<IOJobTarget>?,
  val config: IOBlastConfig
) : IOShortJobResponse(
  id, description, status, created, maxResultSize, parentJobs,
  childJobs, isPrimary, site, targets
)