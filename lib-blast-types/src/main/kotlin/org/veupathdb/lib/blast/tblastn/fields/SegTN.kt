package org.veupathdb.lib.blast.tblastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.common.fields.Seg
import org.veupathdb.lib.blast.util.reqDub
import org.veupathdb.lib.blast.util.reqInt
import org.veupathdb.lib.jackson.Json

private const val KeyWindow = "window"
private const val KeyLocut = "locut"
private const val KeyHicut = "hicut"

private const val DefWindow = 12
private const val DefLoCut  = 2.5
private const val DefHiCut  = 2.5


/**
 * -seg `<String>`
 *
 * Filter query sequence with SEG
 *
 * Format:
 * * yes
 * * "window locut hicut"
 * * no (to disable)
 *
 * Default = `12 2.2 2.5`
 */
sealed interface SegTN : Seg {
  companion object {

    @JvmStatic
    fun yes(): SegTN = YesSegTN

    @JvmStatic
    fun no(): SegTN = NoSegTN

    @JvmStatic
    fun of(window: Int = DefWindow, locut: Double = DefLoCut, hicut: Double = DefHiCut): SegTN =
      ValSegTN(window, locut, hicut)
  }
}


internal fun ParseSegTN(js: ObjectNode): SegTN {
  val tmp = js[FlagSeg] ?: return NoSegTN

  if (tmp.isTextual) {
    return when (js.textValue()) {
      "yes" -> YesSegTN
      "no"  -> NoSegTN
      else  -> throw IllegalArgumentException("$FlagSeg must be an object or one of the string values \"yes\" or \"no\".")
    }
  }

  if (tmp.isObject)
    return parseSeg(tmp as ObjectNode)

  throw IllegalArgumentException("$FlagSeg must be an object or one of the string values \"yes\" or \"no\".")
}


internal object YesSegTN : SegTN {
  override val isYes get() = true

  override val isNo get() = false

  override val window
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyWindow value from \"yes\".")

  override val locut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyLocut value from \"yes\".")

  override val hicut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyHicut value from \"yes\".")

  override val isDefault get() = false

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {
    js.put(FlagSeg, "yes")
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(" $FlagSeg yes")
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(FlagSeg)
    cli.add("yes")
  }
}


internal object NoSegTN : SegTN {
  override val isYes get() = false

  override val isNo get() = false

  override val window
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyWindow value from \"no\".")

  override val locut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyLocut value from \"no\".")

  override val hicut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyHicut value from \"no\".")

  override val isDefault get() = true

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {}

  override fun appendCliSegment(cli: StringBuilder) {}

  override fun appendCliParts(cli: MutableList<String>) {}
}


private fun parseSeg(js: ObjectNode): SegTN {
  val wn = js[KeyWindow] ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyWindow.")
  val ln = js[KeyLocut]  ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyLocut.")
  val hn = js[KeyHicut]  ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyHicut.")

  val w = wn.reqInt { "$FlagSeg.$KeyWindow" }
  val l = ln.reqDub { "$FlagSeg.$KeyLocut" }
  val h = hn.reqDub { "$FlagSeg.$KeyHicut" }

  return ValSegTN(w, l, h)
}


internal data class ValSegTN(
  override val window: Int,
  override val locut: Double,
  override val hicut: Double
) : SegTN {
  override val isYes = false

  override val isNo = false

  override val isDefault
    get() = window == DefWindow && locut == DefLoCut && hicut == DefHiCut

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {
    if (!isDefault) {
      js.set<ObjectNode>(FlagSeg, Json.new<ObjectNode> {
        put(KeyWindow, window)
        put(KeyLocut, locut)
        put(KeyHicut, hicut)
      })
    }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault)
      cli.append(' ')
        .append(FlagSeg)
        .append(" '")
        .append(window)
        .append(' ')
        .append(locut)
        .append(' ')
        .append(hicut)
        .append('\'')
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagSeg)
      cli.add("$window $locut $hicut")
    }
  }
}