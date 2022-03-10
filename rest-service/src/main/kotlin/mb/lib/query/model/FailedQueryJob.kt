package mb.lib.query.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.lib.model.EmptyBlastQueryConfig
import mb.lib.queue.consts.JsonKeys
import mb.lib.queue.model.FailedJob
import mb.lib.queue.model.JobResult
import mb.lib.util.convertJobConfig
import mb.lib.util.jsonArray
import mb.lib.util.jsonCast
import mb.lib.util.jsonObject
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.consts.Key
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

/**
 * Constructs a {@code FailedQueryJob} instance from the given JSON node.
 * <p>
 * We aren't making use of Jackson's reflective unpacking here for
 * compatibility reasons.  Older jobs from the initial beta release store the
 * job CLI config as an array of arguments rather than a structured object.
 * This constructor can unpack either form.
 */
class FailedQueryJob(
  failID:    Int,
  jobID:     Int,
  category:  String,
  url:       String,
  payload:   BlastRequest,
  result:    JobResult,
  failCount: Int,
  failedAt:  OffsetDateTime,
  createdAt: OffsetDateTime,
): FailedJob<BlastRequest>(failID, jobID, category, url, payload, result, failCount, failedAt, createdAt) {

  @JsonCreator
  constructor(raw: ObjectNode) : this(
    raw.get(JsonKeys.ID).asInt(),
    raw.get(JsonKeys.JobID).asInt(),
    raw.get(JsonKeys.Category).asText(),
    raw.get(JsonKeys.URL).asText(),
    raw.toRequest(),
    raw.get(JsonKeys.Result).jsonCast(),
    raw.get(JsonKeys.FailCount).asInt(),
    raw.get(JsonKeys.FailedAt).jsonCast(),
    raw.get(JsonKeys.CreatedAt).jsonCast(),
  )
}

private fun ObjectNode.toRequest(): BlastRequest = when(val c = this.get(JsonKeys.Payload)) {
  is ObjectNode -> BlastRequest(
    HashID(c.get(mb.api.model.io.JsonKeys.JobID).asText()),
    BlastTool.fromString(c.get(mb.api.model.io.JsonKeys.Tool).asText()),
    if (c.path("config").has(Key.Tool))
      convertJobConfig(c.get("config"))
    else
      EmptyBlastQueryConfig(if (c.get("config").isObject) c.get("config") as ObjectNode else jsonObject())
  )

  is ArrayNode -> {
    // Split up the URL to get at the tool name and the job ID.
    // (versions <= v0.9.1 did not pass the job ID or tool in the payload)
    //
    // v <= 0.9.1 url format: http://{blast-service-name}/{blast-tool}/{job-id}
    val urlComponents = this.get(JsonKeys.URL).asText().split("/")

    // Expand array to key value pairs
    val parsedPayload = jsonArray {
      // Append the tool to the array first (the standard format for <= 0.9.1)
      // payloads puts the tool in the array's [0] position.
      add(jsonArray().add(urlComponents[urlComponents.size - 2]))

      // Split each cli arg into a key/value pair and append them to the parsed
      // payload array.
      c.forEach { flag ->
        add(jsonArray {
          val split = flag.textValue().split(delimiters = arrayOf("="), limit = 2)

          add(split[0])

          if (split.size > 1)
            add(split[1])
        })
      }
    }

    BlastRequest(
      HashID(urlComponents[urlComponents.size-1]),
      BlastTool.fromString(urlComponents[urlComponents.size-2]),
      convertJobConfig(parsedPayload)
    )
  }

  else -> throw RuntimeException("Unexpected payload format, must be one of object or array")
}
