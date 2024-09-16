package mb.api.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.module.kotlin.contains
import jakarta.ws.rs.BadRequestException
import mb.api.model.blast.IOBlastConfig
import mb.api.model.dmnd.IODiamondConfig
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.jackson.Json

@JsonDeserialize(using = IOJobConfigDeserializer::class)
sealed interface IOJobConfig<T> {
  val typedConfig: T
}

private class IOJobConfigDeserializer : JsonDeserializer<IOJobConfig<*>>() {
  override fun deserialize(parser: JsonParser, context: DeserializationContext): IOJobConfig<*> {
    val obj = parser.readValueAs(ObjectNode::class.java)

    if (JsonKeys.Tool !in obj)
    // TODO: where is the rest of the validation happening?
      throw BadRequestException("blast job request had no \"tool\" option")

    val tool = obj[JsonKeys.Tool]
      .takeIf { it.isTextual }
      ?.textValue()
      ?: throw BadRequestException("blast job request had an invalid \"tool\" value")

    if (tool.startsWith("diamond-")) {
      obj.put(JsonKeys.Tool, tool.substring(8))
      return IODiamondConfigWrapper(Json.parse(obj))
    }

    return IOBlastConfigWrapper(Json.parse(obj))
  }
}

@JvmInline
internal value class IOBlastConfigWrapper(override val typedConfig: IOBlastConfig) : IOJobConfig<IOBlastConfig>

@JvmInline
internal value class IODiamondConfigWrapper(override val typedConfig: IODiamondConfig) : IOJobConfig<IODiamondConfig>
