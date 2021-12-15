package mb.lib.report.model

import com.fasterxml.jackson.annotation.JsonProperty
import mb.lib.queue.consts.JsonKeys
import mb.lib.queue.model.FailedJob
import mb.lib.queue.model.JobResult
import java.time.OffsetDateTime

class FailedReportJob(
  @JsonProperty(JsonKeys.ID)
  failID: Int,

  @JsonProperty(JsonKeys.JobID)
  jobID: Int,

  @JsonProperty(JsonKeys.Category)
  category: String,

  @JsonProperty(JsonKeys.URL)
  url: String,

  @JsonProperty(JsonKeys.Payload)
  payload: ReportPayload,

  @JsonProperty(JsonKeys.Result)
  result: JobResult? = null,

  @JsonProperty(JsonKeys.FailCount)
  failCount: Int = 0,

  @JsonProperty(JsonKeys.FailedAt)
  failedAt: OffsetDateTime? = null,

  @JsonProperty(JsonKeys.CreatedAt)
  createdAt: OffsetDateTime? = null,

) : FailedJob<ReportPayload>(failID, jobID, category, url, payload, result, failCount, failedAt, createdAt)