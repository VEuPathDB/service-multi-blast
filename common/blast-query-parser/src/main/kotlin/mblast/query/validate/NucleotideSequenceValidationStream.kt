package mblast.query.validate

import mblast.util.io.InStream

/**
 *
 */
class NucleotideSequenceValidationStream(
  maxSequenceLength: Int,
  maxSequenceCount: Int,
  maxTotalLength: Int,
  input: InStream,
) : SequenceValidationStream(
  maxSequenceLength,
  maxSequenceCount,
  maxTotalLength,
  input
) {

  override val sequenceType: SequenceType
    get() = SequenceType.Nucleotide

  override fun isValid(c: Char) =
    when (c) {
      in 'A'..'D', in 'a'..'d' -> true
      in 'G'..'H', in 'g'..'h' -> true
      'K', 'k', 'Y', 'y', '-'  -> true
      in 'M'..'N', in 'm'..'n' -> true
      in 'R'..'W', in 'r'..'w' -> true
      else                     -> false
    }
}
