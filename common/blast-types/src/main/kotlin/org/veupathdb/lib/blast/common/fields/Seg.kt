package org.veupathdb.lib.blast.common.fields

import org.veupathdb.lib.blast.serial.BlastField

/**
 * Low complexity region masker based on the SEG algorithm.
 *
 * Individual Blast+ tools will override/implement this interface as the
 * defaults are different per Blast tool.
 */
interface Seg : BlastField {

  /**
   * Indicates whether this SEG value equals "yes".
   */
  val isYes: Boolean

  /**
   * Indicates whether this SEG value equals "no".
   */
  val isNo: Boolean

  /**
   * SEG window
   *
   * Default = `12`
   *
   * @throws IllegalStateException If this SEG value equals "yes" or "no".
   */
  val window: Int

  /**
   * SEG locut
   *
   * Default = `2.2`
   *
   * @throws IllegalStateException If this SEG value equals "yes" or "no".
   */
  val locut: Double

  /**
   * SEG hicut
   *
   * Default = `2.5`
   *
   * @throws IllegalStateException If this SEG value equals "yes" or "no".
   */
  val hicut: Double
}