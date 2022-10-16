package mblast.query.validate

import mblast.util.io.InStream

class ProteinSequenceValidationStream(
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
    get() = SequenceType.Protein

  override fun isValid(c: Char) =
    when (c) {
      in 'A'..'I', in 'a'..'i' -> true
      in 'K'..'N', in 'k'..'n' -> true
      in 'P'..'Z', in 'p'..'z' -> true
      '-', '*'                 -> true
      else                     -> false
    }
}
