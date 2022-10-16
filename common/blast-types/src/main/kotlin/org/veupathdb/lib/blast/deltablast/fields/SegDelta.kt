package org.veupathdb.lib.blast.deltablast.fields


import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagSeg
import org.veupathdb.lib.blast.common.fields.Seg
import org.veupathdb.lib.blast.util.reqDub
import org.veupathdb.lib.blast.util.reqInt
import org.veupathdb.lib.jackson.Json

private const val KeySeg = "-seg"
private const val KeyWindow = "window"
private const val KeyLocut = "locut"
private const val KeyHicut = "hicut"


sealed interface SegDelta : Seg {
  companion object {

    @JvmStatic
    fun yes(): SegDelta = YesSegD

    @JvmStatic
    fun no(): SegDelta = NoSegD

    @JvmStatic
    fun of(window: Int, locut: Double, hicut: Double): SegDelta =
      ValSegD(window, locut, hicut)
  }
}


internal fun ParseSegDelta(js: ObjectNode): SegDelta {
  val tmp = js[KeySeg] ?: return NoSegD

  if (tmp.isTextual) {
    return when (js.textValue()) {
      "yes" -> YesSegD
      "no"  -> NoSegD
      else  -> throw IllegalArgumentException("$KeySeg must be an object or one of the string values \"yes\" or \"no\".")
    }
  }

  if (tmp.isObject)
    return parseSeg(tmp as ObjectNode)

  throw IllegalArgumentException("$KeySeg must be an object or one of the string values \"yes\" or \"no\".")
}


internal object YesSegD : SegDelta {
  override val isYes get() = true

  override val isNo get() = false

  override val window
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyWindow value from \"yes\".")

  override val locut: Double
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyLocut value from \"yes\".")

  override val hicut: Double
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyHicut value from \"yes\".")

  override val isDefault get() = false

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {
    js.put(KeySeg, "yes")
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(" $KeySeg yes")
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(KeySeg)
    cli.add("yes")
  }
}


internal object NoSegD : SegDelta {
  override val isYes get() = false

  override val isNo get() = true

  override val window
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyWindow value from \"no\".")

  override val locut: Double
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyLocut value from \"no\".")

  override val hicut: Double
    get() = throw IllegalStateException("Cannot get a $KeySeg.$KeyHicut value from \"no\".")

  override val isDefault get() = true

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {}

  override fun appendCliSegment(cli: StringBuilder) {}

  override fun appendCliParts(cli: MutableList<String>) {}
}


private fun parseSeg(js: ObjectNode): SegDelta {
  val wn = js[KeyWindow] ?: throw IllegalArgumentException("Missing required key $KeySeg.$KeyWindow.")
  val ln = js[KeyLocut]  ?: throw IllegalArgumentException("Missing required key $KeySeg.$KeyLocut.")
  val hn = js[KeyHicut]  ?: throw IllegalArgumentException("Missing required key $KeySeg.$KeyHicut.")

  val w = wn.reqInt { "$KeySeg.$KeyWindow" }
  val l = ln.reqDub { "$KeySeg.$KeyLocut" }
  val h = hn.reqDub { "$KeySeg.$KeyHicut" }

  return ValSegD(w, l, h)
}


internal data class ValSegD(
  override val window: Int,
  override val locut: Double,
  override val hicut: Double
) : SegDelta {
  override val isYes = false

  override val isNo = false

  override val isDefault = false

  override val name: String
    get() = FlagSeg

  override fun appendJson(js: ObjectNode) {
    js.set<ObjectNode>(KeySeg, Json.new<ObjectNode> {
      put(KeyWindow, window)
      put(KeyLocut, locut)
      put(KeyHicut, hicut)
    })
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(' ')
      .append(KeySeg)
      .append(" '")
      .append(window)
      .append(' ')
      .append(locut)
      .append(' ')
      .append(hicut)
      .append('\'')
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(KeySeg)
    cli.add("$window $locut $hicut")
  }
}