package org.veupathdb.lib.mblast.sdk.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.veupathdb.lib.mblast.sdk.query.model.TargetSite

class TargetSiteDeserializer : StdDeserializer<TargetSite>(TargetSite::class.java) {
  override fun deserialize(p: JsonParser, ctxt: DeserializationContext) =
    TargetSite.fromJson(p.codec.readTree(p))
}