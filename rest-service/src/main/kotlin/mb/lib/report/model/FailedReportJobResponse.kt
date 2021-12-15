package mb.lib.report.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode
import mb.lib.queue.consts.JsonKeys;
import mb.lib.queue.model.FailedJobResponse;
import mb.lib.util.jsonArray
import mb.lib.util.jsonObject

data class FailedReportJobResponse(
  @get:JsonGetter(JsonKeys.FailedJobs)
  @set:JsonSetter(JsonKeys.FailedJobs)
  override var failedJobs: List<FailedReportJob>
): FailedJobResponse<FailedReportJob> {
  override fun toJSON(): JsonNode {
    return jsonObject { set<JsonNode>(JsonKeys.FailedJobs, jsonArray { failedJobs.forEach { addPOJO(it) } }) }
  }
}
