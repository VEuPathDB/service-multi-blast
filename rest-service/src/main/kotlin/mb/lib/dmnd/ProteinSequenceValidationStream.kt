package mb.lib.dmnd

/**
 * # Protein Sequence Validation Stream
 *
 * Stripped down from the original version in MBlast 2.0 for use with diamond
 * queries.
 */
class ProteinSequenceValidationStream(maxTotalLength: Long, queryStream: InStream)
  : SequenceValidationStream(maxTotalLength, queryStream)
{
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
