package org.veupathdb.lib.blast.blastx.field

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.common.fields.Seg
import org.veupathdb.lib.blast.util.reqDub
import org.veupathdb.lib.blast.util.reqInt
import org.veupathdb.lib.jackson.Json

private const val KeyWindow = "window"
private const val KeyLocut  = "locut"
private const val KeyHicut  = "hicut"

private const val DefWindow = 12
private const val DefLocut  = 2.5
private const val DefHicut  = 2.5


/**
 * -seg `<String>`
 *
 * Filter query sequence with SEG.
 *
 * Format:
 * * yes
 * * "window locut hicut"
 * * no (to disable)
 *
 * Default = `"12 2.2 2.5"`
 */
sealed interface SegX : Seg {
  companion object {

    @JvmStatic
    fun yes(): SegX = YesSegX

    @JvmStatic
    fun no(): SegX = NoSegX

    @JvmStatic
    fun of(window: Int, locut: Double, hicut: Double): SegX =
      ValSegX(window, locut, hicut)
  }
}


internal fun ParseSegX(js: ObjectNode): SegX {
  val tmp = js[FlagSeg] ?: return NoSegX

  if (tmp.isTextual) {
    return when (js.textValue()) {
      "yes" -> YesSegX
      "no"  -> NoSegX
      else  -> throw IllegalArgumentException("$FlagSeg must be an object or one of the string values \"yes\" or \"no\".")
    }
  }

  if (tmp.isObject)
    return parseSeg(tmp as ObjectNode)

  throw IllegalArgumentException("$FlagSeg must be an object or one of the string values \"yes\" or \"no\".")
}


internal object YesSegX : SegX {
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


internal object NoSegX : SegX {
  override val isYes get() = false

  override val isNo get() = true

  override val window
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyWindow value from \"no\".")

  override val locut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyLocut value from \"no\".")

  override val hicut: Double
    get() = throw IllegalStateException("Cannot get a $FlagSeg.$KeyHicut value from \"no\".")

  override val isDefault get() = false

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {}

  override fun appendCliSegment(cli: StringBuilder) {}

  override fun appendCliParts(cli: MutableList<String>) {}
}


private fun parseSeg(js: ObjectNode): SegX {
  val wn = js[KeyWindow] ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyWindow.")
  val ln = js[KeyLocut]  ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyLocut.")
  val hn = js[KeyHicut]  ?: throw IllegalArgumentException("Missing required key $FlagSeg.$KeyHicut.")

  val w = wn.reqInt { "$FlagSeg.$KeyWindow" }
  val l = ln.reqDub { "$FlagSeg.$KeyLocut" }
  val h = hn.reqDub { "$FlagSeg.$KeyHicut" }

  return ValSegX(w, l, h)
}


internal data class ValSegX(
  override val window: Int    = DefWindow,
  override val locut:  Double = DefLocut,
  override val hicut:  Double = DefHicut,
) : SegX {
  override val isYes = false

  override val isNo = false

  override val isDefault =
    window == DefWindow && locut == DefLocut && hicut == DefHicut

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
    if (!isDefault) {
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
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagSeg)
      cli.add("$window $locut $hicut")
    }
  }
}