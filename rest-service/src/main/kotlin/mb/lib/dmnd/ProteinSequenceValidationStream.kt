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
  private val chars = HashSet<Char>()

  override val sequenceType: SequenceType
    get() = SequenceType.Protein

  override fun onSequenceCharacter(c: Char) {
    super.onSequenceCharacter(c)
    chars.add(c.lowercaseChar())
  }

  override fun isValid(c: Char) =
    when (c) {
      in 'A'..'I', in 'a'..'i' -> true
      in 'K'..'N', in 'k'..'n' -> true
      in 'P'..'Z', in 'p'..'z' -> true
      '-', '*'                 -> true
      else                     -> false
    }

  override fun onQueryComplete() {
    super.onQueryComplete()

    if (chars.size < 5) {
      for (char in chars) {
        when (char) {
          // If the character is a DNA character, keep checking
          'a', 'c', 'g', 't' -> continue
          // If the character is not a DNA character, then we're all good.
          else               -> return
        }
      }

      // If we got here, then the only distinct characters we've seen are in the
      // DNA character set.  DIAMOND will throw an error for this, so reject the
      // query now before we queue or store the job.
      throw DNASequenceException()
    }
  }
}
