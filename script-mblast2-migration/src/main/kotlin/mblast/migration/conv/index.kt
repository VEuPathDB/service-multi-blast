package mblast.migration.conv

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.BooleanNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import mblast.migration.db.model.MultiBlastJobsRow
import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.blastn.fields.Dust
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.jackson.Json

fun MultiBlastJobsRow.getBlastConfig(): BlastQueryBase =
  when (jobConfig) {
    is ArrayNode  -> jobConfig.blastConfigFromArray()
    is ObjectNode -> jobConfig.blastConfigFromObject()
    else          -> throw IllegalStateException()
  }

private fun ObjectNode.putParse(key: String, other: JsonNode): ObjectNode {
  other.textValue().also {
    try { return put(key, it.toInt())           } catch (e: Throwable) {}
    try { return put(key, it.toDouble())        } catch (e: Throwable) {}
    try { return put(key, it.toBooleanStrict()) } catch (e: Throwable) {}
    return put(key, it)
  }
}

private fun ArrayNode.blastConfigFromArray(): BlastQueryBase =
  Json.newObject {
    set<JsonNode>("tool", this@blastConfigFromArray[0][0])
    for (i in 1 until this@blastConfigFromArray.size())
      this@blastConfigFromArray[i].also {
        putParse(it[0].textValue(), it[1] ?: TextNode("true"))
      }
  }
    .apply {
      remove(FlagQueryFile)
      remove(FlagOutFormat)
      remove(FlagDBFile)
    }
    .apply {
      fixDust()
      fixSeg()
    }
    .let { Blast.of(it) } as BlastQueryBase

private fun ObjectNode.blastConfigFromObject(): BlastQueryBase =
  apply {
    remove(FlagQueryFile)
    remove(FlagOutFormat)
    remove(FlagDBFile)
  }.let { Blast.of(this) as BlastQueryBase }


private val DustRegex = Regex("^(\\d+) +(\\d+) +(\\d+)$")

private fun ObjectNode.fixDust() {
  val dust = get(FlagDust) ?: return

  if (dust.isObject)
    return

  val (level, window, linker) = DustRegex.matchEntire(dust.textValue())!!.destructured
  remove(FlagDust)
  Dust.of(level.toInt(), window.toInt(), linker.toInt()).appendJson(this)
}

private fun ObjectNode.fixSeg() {
  val seg = get(FlagSeg) ?: return

  if (seg.isObject)
    return

  if (seg.textValue() == "12 2.20000000000000017763568394002504646778106689453125 2.5") {
    put(FlagSeg, "yes")
  }

}