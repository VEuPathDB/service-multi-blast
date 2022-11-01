package org.veupathdb.lib.mblast.sdk.util

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.veupathdb.lib.mblast.sdk.query.blast.*

class BlastQueryConfigDeserializer : StdDeserializer<BlastQueryConfig>(BlastQueryConfig::class.java) {
  override fun deserialize(p: JsonParser, ctxt: DeserializationContext): BlastQueryConfig {
    val raw = p.codec.readTree<JsonNode>(p)

    if (!raw.isObject)
      throw IllegalStateException("blastConfig must be an object")

    if (!raw.has("tool"))
      throw IllegalStateException("blastConfig must contain the key \"tool\"")

    return when (BlastQueryTool.fromJson(raw["tool"])) {
      BlastQueryTool.BlastN     -> p.codec.treeToValue(raw, BlastNConfig::class.java)
      BlastQueryTool.BlastP     -> p.codec.treeToValue(raw, BlastPConfig::class.java)
      BlastQueryTool.BlastX     -> p.codec.treeToValue(raw, BlastXConfig::class.java)
      BlastQueryTool.DeltaBlast -> p.codec.treeToValue(raw, DeltaBlastConfig::class.java)
      BlastQueryTool.PSIBlast   -> p.codec.treeToValue(raw, PSIBlastConfig::class.java)
      BlastQueryTool.RPSBlast   -> p.codec.treeToValue(raw, RPSBlastConfig::class.java)
      BlastQueryTool.RPSTBlastN -> p.codec.treeToValue(raw, RPSTBlastNConfig::class.java)
      BlastQueryTool.TBlastN    -> p.codec.treeToValue(raw, TBlastNConfig::class.java)
      BlastQueryTool.TBlastX    -> p.codec.treeToValue(raw, TBlastXConfig::class.java)
    }
  }
}