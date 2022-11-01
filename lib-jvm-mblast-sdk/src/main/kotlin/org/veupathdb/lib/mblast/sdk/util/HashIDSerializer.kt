package org.veupathdb.lib.mblast.sdk.util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.veupathdb.lib.hash_id.HashID

class HashIDSerializer : StdSerializer<HashID>(HashID::class.java) {
  override fun serialize(value: HashID, gen: JsonGenerator, provider: SerializerProvider) =
    gen.writeString(value.string)
}