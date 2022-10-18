package org.veupathdb.lib.blast.common.fields

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.blast.common.FlagDust
import org.veupathdb.lib.blast.common.FlagXDropFinalGap
import org.veupathdb.lib.blast.common.FlagXDropPrelimGap
import org.veupathdb.lib.blast.common.FlagXDropUngap
import org.veupathdb.lib.blast.serial.BlastField
import org.veupathdb.lib.blast.util.*


internal fun ParseXDropUngap(js: ObjectNode) =
  js.optDub(FlagXDropUngap) { ExtensionDropoffUngapped(it) }
    ?: ExtensionDropoffUngapped()


/**
 * -xdrop_ungap `<Real>`
 *
 * X-dropoff value (in bits) for ungapped extensions
 */
@JvmInline
value class ExtensionDropoffUngapped(val value: Double = Double.NaN) : BlastField {
  override val isDefault get() = value.isNaN()

  override val name: String
    get() = FlagXDropUngap

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagXDropUngap, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagXDropUngap, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagXDropUngap, value)
}


internal fun ParseXDropGap(js: ObjectNode) =
  js.optDub(FlagXDropPrelimGap) { ExtensionDropoffPrelimGapped(it) }
    ?: ExtensionDropoffPrelimGapped()


/**
 * -xdrop_gap `<Real>`
 *
 * X-dropoff value (in bits) for preliminary gapped extensions
 */
@JvmInline
value class ExtensionDropoffPrelimGapped(val value: Double = Double.NaN) : BlastField {
  override val isDefault get() = value.isNaN()

  override val name: String
    get() = FlagXDropPrelimGap

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagXDropPrelimGap, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagXDropPrelimGap, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagXDropPrelimGap, value)
}


internal fun ParseXDropGapFinal(js: ObjectNode) =
  js.optDub(FlagXDropFinalGap) { ExtensionDropoffFinalGapped(it) }
    ?: ExtensionDropoffFinalGapped()


/**
 * -xdrop_gap_final `<Real>`
 *
 * X-dropoff value (in bits) for final gapped alignment
 */
@JvmInline
value class ExtensionDropoffFinalGapped(val value: Double = Double.NaN) : BlastField {
  override val isDefault get() = value.isNaN()

  override val name: String
    get() = FlagXDropFinalGap

  override fun appendJson(js: ObjectNode) =
    js.put(isDefault, FlagXDropFinalGap, value)

  override fun appendCliSegment(cli: StringBuilder) =
    cli.append(isDefault, FlagXDropFinalGap, value)

  override fun appendCliParts(cli: MutableList<String>) =
    cli.add(isDefault, FlagXDropFinalGap, value)
}
