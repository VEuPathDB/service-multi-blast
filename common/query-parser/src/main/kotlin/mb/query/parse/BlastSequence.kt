package mb.query.parse

/**
 * Blast Sequence
 *
 * Represents a single sequence in a multi-sequence query.
 */
data class BlastSequence(

  /**
   * Defline for the sequence.
   */
  val defLine: String?,

  /**
   * Sequence Body.
   */
  val sequence: String,
) {

  override fun toString() =
    defLine?.let { "$it\n$sequence" } ?: sequence
}