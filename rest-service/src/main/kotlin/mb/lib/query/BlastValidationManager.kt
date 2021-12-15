package mb.lib.query

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import mb.api.service.model.ErrorMap
import mb.api.service.util.Address
import mb.lib.config.Config
import mb.lib.util.jsonStringify
import mb.lib.util.parseJSON
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.blast.BlastConfig
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.util.*

object BlastValidationManager {
  private val Log  = LogManager.getLogger(BlastValidationManager::class)

  private const val Path = "validate"

  fun validate(conf: BlastConfig): ErrorMap? {
    Log.trace("::Validate(conf={})", conf)

    val payload = conf.jsonStringify()
    val uri     = Address.http(java.lang.String.join("/", Config.validatorHost, Path, conf.tool.value))
    Log.debug("Sending config to {}: {}", uri, payload)

    val res = HttpClient.newHttpClient()
      .send(
        HttpRequest.newBuilder()
          .POST(HttpRequest.BodyPublishers.ofString(payload))
          .uri(URI.create(uri))
          .build(),
        HttpResponse.BodyHandlers.ofString()
      )

    if (res.statusCode() != 200)
      throw RuntimeException("Unexpected response from validation server: " + res.body())

    val parsed = res.body().parseJSON<ValidationResponse>()

    Log.debug("JSON Reply: {}", res.body())
    if (parsed.payload.isEmpty())
      return null

    val converted = ErrorMap(parsed.payload.size)

    for ((key, value) in parsed.payload) {
      val flag = Field2Flag.Flag2Field[key] ?: continue

      converted[flag] = value
    }

    return converted
  }
}

private data class ValidationResponse(
  @JsonProperty("status")  val status: Int,
  @JsonProperty("payload") val payload: Map<String, MutableList<String>> = emptyMap()
)