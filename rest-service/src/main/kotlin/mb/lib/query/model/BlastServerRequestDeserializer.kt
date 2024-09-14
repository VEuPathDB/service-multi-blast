package mb.lib.query.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.jackson.Json

internal class BlastServerRequestDeserializer : JsonDeserializer<BlastServerRequest>() {
  override fun deserialize(parser: JsonParser, ctxt: DeserializationContext?): BlastServerRequest {
    val obj = parser.readValueAs(ObjectNode::class.java)

    return if (obj[JsonKeys.Tool]?.textValue()?.startsWith("diamond-") == true)
      Json.parse<DiamondRequest>(obj)
    else
      Json.parse<BlastRequest>(obj)
  }
}
