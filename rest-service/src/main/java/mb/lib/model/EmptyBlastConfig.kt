package mb.lib.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.consts.Key
import org.veupathdb.lib.blast.field.*

import java.util.function.Function

/**
 * Empty Blast Config
 * <p>
 * Represents an invalid blast configuration that may be left over in various
 * places if there is an issue with the config serialization.
 */
open class EmptyBlastConfig(protected val raw: ObjectNode): BlastConfig {

  protected var tmp: JsonNode? = null

  override fun getTool() = get(Key.Tool, BlastTool::fromJSON)

  override fun getShortHelp() = getBoolean(Flag.ShortHelp)

  override fun setShortHelp(aBoolean: Boolean?) {
    raw.put(Flag.ShortHelp, aBoolean)
  }

  override fun getLongHelp() = getBoolean(Flag.LongHelp)

  override fun setLongHelp(aBoolean: Boolean?) {
    raw.put(Flag.LongHelp, aBoolean)
  }

  override fun getVersion() = getBoolean(Flag.Version)

  override fun setVersion(aBoolean: Boolean?) {
    raw.put(Flag.Version, aBoolean)
  }

  override fun getOutFile() = fromString(Flag.OutFile, ::OutFile)

  override fun setOutFile(s: String) {
    raw.set<JsonNode>(Flag.OutFile, OutFile(s).toJSON())
  }

  override fun getOutFormat() = get(Flag.OutFormat, OutFormat::fromJSON)

  override fun setOutFormat(outFormat: OutFormat) {
    raw.set<JsonNode>(Flag.OutFormat, outFormat.toJSON())
  }

  override fun getShowGIs() = getBoolean(Flag.ShowGIs)

  override fun setShowGIs(aBoolean: Boolean?) {
    raw.put(Flag.ShowGIs, aBoolean)
  }

  override fun getNumDescriptions() = fromLong(Flag.NumDescriptions, ::NumDescriptions)

  override fun setNumDescriptions(aLong: Long?) {
    raw.put(Flag.NumDescriptions, aLong)
  }

  override fun getNumAlignments() = fromLong(Flag.NumAlignments, ::NumAlignments)

  override fun setNumAlignments(aLong: Long?) {
    raw.put(Flag.NumAlignments, aLong)
  }

  override fun getLineLength() = get(Flag.LineLength, JsonNode::intValue, ::LineLength)

  override fun setLineLength(integer: Int?) {
    raw.put(Flag.LineLength, integer)
  }

  override fun getHTML() = getBoolean(Flag.HTML)

  override fun setHTML(aBoolean: Boolean?) {
    raw.put(Flag.HTML, aBoolean)
  }

  override fun getSortHits() = get(Flag.SortHits, HitSorting::fromJSON)

  override fun setSortHits(hitSorting: HitSorting) {
    raw.set<JsonNode>(Flag.SortHits, hitSorting.toJSON())
  }

  override fun getSortHSPs() = get(Flag.SortHSPs, HSPSorting::fromJSON)

  override fun setSortHSPs(hspSorting: HSPSorting) {
    raw.set<JsonNode>(Flag.SortHSPs, hspSorting.toJSON())
  }

  override fun getMaxTargetSequences() = fromLong(Flag.MaxTargetSequences, ::MaxTargetSeqs)

  override fun setMaxTargetSequences(aLong: Long?) {
    raw.put(Flag.MaxTargetSequences, aLong)
  }

  override fun getParseDefLines() = getBoolean(Flag.ParseDefLines)

  override fun setParseDefLines(aBoolean: Boolean?) {
    raw.put(Flag.ParseDefLines, aBoolean)
  }

  //
  //
  // Internal
  //
  //

  protected fun <T> fromLong(key: String, fn: Function<Long, T>) =
    if (toTmp(key) && !tmp!!.isNull) fn.apply(tmp!!.longValue()) else null

  protected fun <T> fromString(key: String, fn: Function<String, T>) =
    if (toTmp(key) && !tmp!!.isNull) fn.apply(tmp!!.textValue()) else null

  protected fun getLong(key: String) = if (toTmp(key) && !tmp!!.isNull) tmp!!.longValue() else null

  protected fun getBoolean(key: String) = if (toTmp(key) && !tmp!!.isNull) tmp!!.booleanValue() else null

  protected fun getString(key: String) = if (toTmp(key) && !tmp!!.isNull) tmp!!.textValue() else null

  protected fun <T> get(key: String, fn: Function<JsonNode, T>) =
    if (toTmp(key) && !tmp!!.isNull) fn.apply(tmp!!) else null

  protected fun <T, I> get(key: String, g: Function<JsonNode, I>, m: Function<I, T>) =
    if (toTmp(key) && !tmp!!.isNull) m.apply(g.apply(tmp!!)) else null

  protected fun toTmp(key: String): Boolean {
    tmp = raw.get(key)
    return tmp != null
  }
}
