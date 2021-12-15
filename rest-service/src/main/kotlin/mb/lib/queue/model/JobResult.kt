package mb.lib.queue.model

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import mb.lib.queue.consts.JsonKeys

data class JobResult(
  @get:JsonGetter(JsonKeys.Status)
  @set:JsonSetter(JsonKeys.Status)
  var status: String? = null,

  @get:JsonGetter(JsonKeys.Code)
  @set:JsonSetter(JsonKeys.Code)
  var code: Int = 0,

  @get:JsonGetter(JsonKeys.Message)
  @set:JsonSetter(JsonKeys.Message)
  var message: String? = null
)