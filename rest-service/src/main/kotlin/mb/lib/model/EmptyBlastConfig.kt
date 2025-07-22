package mb.lib.model

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.consts.Flag
import org.veupathdb.lib.blast.consts.Key
import org.veupathdb.lib.blast.field.*

/**
 * Empty Blast Config
 * <p>
 * Represents an invalid blast configuration that may be left over in various
 * places if there is an issue with the config serialization.
 */
open class EmptyBlastConfig(protected val raw: ObjectNode): BlastConfig {

  protected var tmp: JsonNode? = null

  override val tool: BlastTool
    get() = get(Key.Tool, BlastTool::fromJSON)!!

  override var shortHelp: Boolean?
    get() = getBoolean(Flag.ShortHelp)
    set(v) {
      raw.put(Flag.ShortHelp, v)
    }

  override var longHelp: Boolean?
    get() = getBoolean(Flag.LongHelp)
    set(v) {
      raw.put(Flag.LongHelp, v)
    }

  override var version: Boolean?
    get() = getBoolean(Flag.Version)
    set(v) {
      raw.put(Flag.Version, v)
    }

  override var outFile: OutFile?
    get() = fromString(Flag.OutFile, ::OutFile)
    set(v) {
      raw.set<JsonNode>(Flag.OutFile, v?.toJSON())
    }

  override var outFormat: OutFormat?
    get() = get(Flag.OutFormat, OutFormat::fromJSON)
    set(v) {
      raw.set<JsonNode>(Flag.OutFormat, v?.toJSON())
    }

  override var showGIs: Boolean?
    get() = getBoolean(Flag.ShowGIs)
    set(v) {
      raw.put(Flag.ShowGIs, v)
    }

  override var numDescriptions
    get() = fromLong(Flag.NumDescriptions, ::NumDescriptions)
    set(v) {
      raw.put(Flag.NumDescriptions, v?.value)
    }

  override var numAlignments
    get() = fromLong(Flag.NumAlignments, ::NumAlignments)
    set(v) {
      raw.put(Flag.NumAlignments, v?.value)
    }

  override var lineLength: LineLength?
    get() = fromInt(Flag.LineLength, ::LineLength)
    set(v) {
      raw.put(Flag.LineLength, v?.value)
    }

  override var html: Boolean?
    get() = TODO("Not yet implemented")
    set(v) {
      raw.put(Flag.HTML, v)
    }

  override var sortHits: HitSorting?
    get() = get(Flag.SortHits, HitSorting::fromJSON)
    set(v) {
      raw.set<JsonNode>(Flag.SortHits, v?.toJSON())
    }

  override var sortHSPs: HSPSorting?
    get() = get(Flag.SortHSPs, HSPSorting::fromJSON)
    set(v) {
      raw.set<JsonNode>(Flag.SortHSPs, v?.toJSON())
    }

  override var maxTargetSequences: MaxTargetSeqs?
    get() = fromLong(Flag.MaxTargetSequences, ::MaxTargetSeqs)
    set(v) {
      raw.put(Flag.MaxTargetSequences, v?.value)
    }

  override var parseDefLines: Boolean?
    get() = getBoolean(Flag.ParseDefLines)
    set(v) {
      raw.put(Flag.ParseDefLines, v)
    }

  //
  //
  // Internal
  //
  //

  protected inline fun <T> fromInt(key: String, fn: (Int) -> T) =
    if (toTmp(key) && !tmp!!.isNull) fn(tmp!!.intValue()) else null

  protected inline fun <T> fromLong(key: String, fn: (Long) -> T) =
    if (toTmp(key) && !tmp!!.isNull) fn(tmp!!.longValue()) else null

  protected inline fun <T> fromString(key: String, fn: (String) -> T) =
    if (toTmp(key) && !tmp!!.isNull) fn(tmp!!.textValue()) else null

  protected inline fun getLong(key: String) =
    if (toTmp(key) && !tmp!!.isNull) tmp!!.longValue() else null

  protected inline fun getBoolean(key: String) =
    if (toTmp(key) && !tmp!!.isNull) tmp!!.booleanValue() else null

  protected inline fun getString(key: String) =
    if (toTmp(key) && !tmp!!.isNull) tmp!!.textValue() else null

  protected inline fun <T> get(key: String, fn: (JsonNode) -> T) =
    if (toTmp(key) && !tmp!!.isNull) fn(tmp!!) else null

  protected inline fun <T, I> get(key: String, g: (JsonNode) -> I, m: (I) -> T) =
    if (toTmp(key) && !tmp!!.isNull) m(g(tmp!!)) else null

  protected fun toTmp(key: String): Boolean {
    tmp = raw.get(key)
    return tmp != null
  }
}
