package mb.lib.query.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import mb.lib.queue.consts.JsonKeys
import mb.lib.queue.model.FailedJobResponse
import mb.lib.util.jsonCast
import mb.lib.util.jsonObject

data class FailedQueryJobResponse(
  @JsonProperty(JsonKeys.FailedJobs) override val failedJobs: List<FailedQueryJob>
) : FailedJobResponse<FailedQueryJob> {
  override fun toJSON() = jsonObject { set<JsonNode>(JsonKeys.FailedJobs, failedJobs.jsonCast()) }
}
