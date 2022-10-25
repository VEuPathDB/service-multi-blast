package mblast.migration.conv

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import mblast.migration.db.model.MultiBlastJobsRow
import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.blastn.fields.Dust
import org.veupathdb.lib.blast.common.*
import org.veupathdb.lib.jackson.Json

/**
 * Parses a [BlastQueryBase] instance out of the raw row JSON.
 *
 * The raw json may be a JSON object that already represents a `BlastQueryBase`
 * instance (for mblast v1.x) or a JSON array that contains the CLI options (for
 * mblast v0.x).
 *
 * @receiver Database row containing the config to parse.
 *
 * @return The parsed config.
 */
fun MultiBlastJobsRow.getBlastConfig(): BlastQueryBase =
  when (jobConfig) {
    is ArrayNode  -> jobConfig.blastConfigFromArray()
    is ObjectNode -> jobConfig.blastConfigFromObject()
    else          -> throw IllegalStateException()
  }

/**
 * Parses the json node value into the correct type before attempting to put it
 * into the target JSON object.
 *
 * This is done to handle the mblast v0.x JSON array syntax, where all CLI args
 * were translated to strings.
 *
 * Example Input:
 * ```json
 * [["blastn"],["-db_size", "3"],["-use_sw_tback","true"]]
 * ```
 *
 * @receiver Target JSON object that the parsed value will be appended to.
 *
 * @param key Key under which the parsed value should be set.
 *
 * @param other Raw JSON value to parse before setting.
 */
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
    // In the mblast v0.x JSON array config, the first sub-array contains a
    // single value, which is the name of the BLAST+ tool the config is for.
    set<JsonNode>("tool", this@blastConfigFromArray[0][0])

    // All subsequent sub-arrays are key-value pairs (except when they aren't).
    for (i in 1 until this@blastConfigFromArray.size())
      this@blastConfigFromArray[i].also {
        putParse(it[0].textValue(), it[1] ?: TextNode("true"))
      }
  }
    // Strip out the junk we don't want to store.
    .apply {
      remove(FlagQueryFile)
      remove(FlagOutFormat)
      remove(FlagDBFile)
    }
    // Fix the multi-field args
    .apply {
      parseDust()
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


private fun ObjectNode.parseDust() {
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