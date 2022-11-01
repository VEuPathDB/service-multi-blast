package org.veupathdb.lib.mblast.sdk.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.veupathdb.lib.hash_id.HashID

class HashIDDeserializer : StdDeserializer<HashID>(HashID::class.java) {
  override fun deserialize(p: JsonParser, ctxt: DeserializationContext) = HashID(p.text)
}