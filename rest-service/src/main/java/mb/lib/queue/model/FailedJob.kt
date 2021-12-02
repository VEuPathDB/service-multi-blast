package mb.lib.queue.model;

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import mb.lib.queue.consts.JsonKeys
import java.time.OffsetDateTime

open class FailedJob<T>(
  @get:JsonGetter(JsonKeys.ID)
  @set:JsonSetter(JsonKeys.ID)
  open var failID: Int,

  @get:JsonGetter(JsonKeys.JobID)
  @set:JsonSetter(JsonKeys.JobID)
  open var jobID: Int,

  @get:JsonGetter(JsonKeys.Category)
  @set:JsonSetter(JsonKeys.Category)
  open var category: String,

  @get:JsonGetter(JsonKeys.URL)
  @set:JsonSetter(JsonKeys.URL)
  open var url: String,

  @get:JsonGetter(JsonKeys.Payload)
  @set:JsonSetter(JsonKeys.Payload)
  open var payload: T,

  @get:JsonGetter(JsonKeys.Result)
  @set:JsonSetter(JsonKeys.Result)
  open var result: JobResult? = null,

  @get:JsonGetter(JsonKeys.FailCount)
  @set:JsonSetter(JsonKeys.FailCount)
  open var failCount: Int = 0,

  @get:JsonGetter(JsonKeys.FailedAt)
  @set:JsonSetter(JsonKeys.FailedAt)
  open var failedAt: OffsetDateTime? = null,

  @get:JsonGetter(JsonKeys.CreatedAt)
  @set:JsonSetter(JsonKeys.CreatedAt)
  open var createdAt: OffsetDateTime? = null,
)