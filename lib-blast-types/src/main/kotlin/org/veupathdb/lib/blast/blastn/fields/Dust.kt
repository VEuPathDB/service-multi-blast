package org.veupathdb.lib.blast.blastn.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.serial.BlastField


private const val KeyLevel  = "level"
private const val KeyWindow = "window"
private const val KeyLinker = "linker"
private const val DefLevel  = 20
private const val DefWindow = 64
private const val DefLinker = 1


/**
 * -dust `<String>`
 *
 * Filter query sequence with DUST (Format: 'yes', 'level window linker', or
 * 'no' to disable)
 *
 * Default = `"20 64 1"`
 */
sealed interface Dust : BlastField {

  /**
   * Indicates whether this Dust value is `"yes"`.
   */
  val isYes: Boolean

  /**
   * Indicates whether this Dust value is `"no"`.
   */
  val isNo: Boolean

  /**
   * The level value for a value based Dust value.
   *
   * DUST level (score threshold for subwindows)
   *
   * Default = `20`
   *
   * @throws IllegalStateException If the current Dust implementation is a
   * `"yes"` or `"no"` value.
   */
  val level: Int

  /**
   * The window value for a value based Dust value.
   *
   * DUST window length
   *
   * Default = `64`
   *
   * @throws IllegalStateException If the current Dust implementation is a
   * `"yes"` or `"no"` value.
   */
  val window: Int

  /**
   * The linker value for a value based Dust value.
   *
   * DUST linker (how close masked intervals should be to get merged together).
   *
   * Default = `1`
   *
   * @throws IllegalStateException If the current Dust implementation is a
   * `"yes"` or `"no"` value.
   */
  val linker: Int

  companion object {

    /**
     * Returns a `"yes"` Dust value.
     */
    @JvmStatic
    fun yes(): Dust = YesDust

    /**
     * Returns a `"no"` Dust value.
     */
    @JvmStatic
    fun no(): Dust = NoDust

    /**
     * Returns a value based Dust value wrapping the given inputs.
     *
     * @param level Value Dust level.
     *
     * @param window Value Dust window.
     *
     * @param linker Value Dust linker.
     */
    @JvmStatic
    fun of(
      level:  Int = DefLevel,
      window: Int = DefWindow,
      linker: Int = DefLinker,
    ): Dust =
      ValDust(level, window, linker)
  }
}


////////////////////////////////////////////////////////////////////////////////


internal fun ParseDust(js: ObjectNode): Dust {
  val tmp = js[FlagDust] ?: return ValDust()

  if (tmp.isTextual)
    return parseTextDust(tmp.textValue())

  if (tmp.isObject)
    return parseValDust(tmp as ObjectNode)

  throw IllegalArgumentException("Invalid $FlagDust value $tmp")
}


////////////////////////////////////////////////////////////////////////////////


private fun parseValDust(js: ObjectNode): ValDust {
  val nLevel  = js[KeyLevel]
  val nWindow = js[KeyWindow]
  val nLinker = js[KeyLinker]

  if (nLevel != null && !nLevel.isIntegralNumber)
    throw IllegalArgumentException("$FlagDust.$KeyLevel must be an int value.")
  if (nWindow != null && !nWindow.isIntegralNumber)
    throw IllegalArgumentException("$FlagDust.$KeyWindow must be an int value.")
  if (nLinker != null && !nLinker.isIntegralNumber)
    throw IllegalArgumentException("$FlagDust.$KeyLinker must be an int value.")

  return ValDust(
    nLevel?.intValue() ?: DefLevel,
    nWindow?.intValue() ?: DefWindow,
    nLinker?.intValue() ?: DefLinker,
  )
}


////////////////////////////////////////////////////////////////////////////////


internal data class ValDust(
  override val level:  Int = DefLevel,
  override val window: Int = DefWindow,
  override val linker: Int = DefLinker,
) : Dust {
  override val isYes get() = false

  override val isNo get() = false

  override val isDefault
    get() = level == DefLevel && window == DefWindow && linker == DefLinker

  override val name: String
    get() = FlagDust

  override fun appendJson(js: ObjectNode) {
    if (isDefault)
      return

    with(js.putObject(FlagDust)) {
      put(KeyLevel, level)
      put(KeyWindow, window)
      put(KeyLinker, linker)
    }
  }

  override fun appendCliSegment(cli: StringBuilder) {
    if (!isDefault)
      cli.append(' ')
        .append(FlagDust)
        .append(" '")
        .append(level)
        .append(' ')
        .append(window)
        .append(' ')
        .append(linker)
        .append('\'')
  }

  override fun appendCliParts(cli: MutableList<String>) {
    if (!isDefault) {
      cli.add(FlagDust)
      cli.add("$level $window $linker")
    }
  }
}


////////////////////////////////////////////////////////////////////////////////


private fun parseTextDust(value: String) =
  when(value) {
    "yes" -> YesDust
    "no"  -> NoDust
    else  -> throw IllegalArgumentException("Invalid dust value $value")
  }


////////////////////////////////////////////////////////////////////////////////


internal object YesDust : Dust {
  override val isYes get() = true

  override val isNo  get() = false

  override val level
    get() = throw IllegalStateException("Cannot get the level value from a \"yes\" dust argument.")

  override val window
    get() = throw IllegalStateException("Cannot get the window value from a \"yes\" dust argument.")

  override val linker
    get() = throw IllegalStateException("Cannot get the linker value from a \"yes\" dust argument.")

  override val isDefault get() = false

  override val name: String
    get() = FlagDust

  override fun appendJson(js: ObjectNode) {
    js.put(FlagDust, "yes")
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(' ').append(FlagDust).append(' ').append("yes")
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(FlagDust)
    cli.add("yes")
  }
}


////////////////////////////////////////////////////////////////////////////////


internal object NoDust : Dust {
  override val isYes get() = false

  override val isNo  get() = true

  override val level: Int
    get() = throw IllegalStateException("Cannot get the level value from a \"no\" dust argument.")

  override val window: Int
    get() = throw IllegalStateException("Cannot get the window value from a \"no\" dust argument.")

  override val linker: Int
    get() = throw IllegalStateException("Cannot get the linker value from a \"no\" dust argument.")

  override val isDefault get() = false

  override val name: String
    get() = FlagDust

  override fun appendJson(js: ObjectNode) {
    js.put(FlagDust, "no")
  }

  override fun appendCliSegment(cli: StringBuilder) {
    cli.append(' ').append(FlagDust).append(' ').append("no")
  }

  override fun appendCliParts(cli: MutableList<String>) {
    cli.add(FlagDust)
    cli.add("no")
  }
}
