package mb.lib.dmnd

/**
 * # BLAST Multi-Sequence Query Validation Stream
 *
 * Stripped down from the original version in MBlast 2.0 for use with diamond
 * queries.
 */
abstract class SequenceValidationStream(
  private val maxTotalQueryLength: Long,
  private val stream: InStream
) : SequenceStateStream(), InStream {

  /**
   * Maximum number of characters to check on the first line for the hyphen
   * count validation.
   */
  private val maxFirstLineCheckingChars = 70
  private var lookingForDashes = false
  private var dashesOnFirstLine = 0
  private var nonDashesOnFirstLine = 0

  private var currentSequenceLength = 0
  private var totalQueryLength = 0L

  /**
   * Type of sequences that an implementation of this class handles.
   */
  abstract val sequenceType: SequenceType

  /**
   * Tests whether the given non-ignorable sequence character is valid for the
   * target sequence type.
   *
   * @param c Character to test.
   *
   * @return `true` if the given character is valid for the target sequence
   * type, otherwise `false`.
   */
  protected abstract fun isValid(c: Char): Boolean

  override fun nextCharacter() = stream.read()

  override fun onQueryComplete() {
    // If there were no non-ignorable characters in the entire query stream then
    // throw an error.
    if (totalQueryLength == 0L)
      throw EmptySequenceException(1, lineNumber)

    // If there were no sequence characters in the last sequence we processed
    // then throw an error.
    if (currentSequenceLength == 0)
      throw EmptySequenceException(sequenceCount, lineNumber)
  }

  override fun onSequenceStart() {
    // Check the first line of the new sequence to ensure there are not too many
    // dashes (which will cause the BLAST+ CLI tool to fail).
    lookingForDashes = true
    dashesOnFirstLine = 0
    nonDashesOnFirstLine = 0

    // If we are not on the first sequence (meaning this is not the first call
    // to this method) AND the previous sequence had no non-ignorable characters
    // then throw an empty sequence error
    if (sequenceCount > 1 && currentSequenceLength == 0)
      throw EmptySequenceException(sequenceCount, currentSequenceStartLineNumber)

    // Reset the current sequence length counter for the new sequence.
    currentSequenceLength = 0
  }

  override fun onSequenceLineEnd() {
    // If we were looking for dashes (on the first sequence line) then...
    if (lookingForDashes) {
      // We are no longer looking for dashes
      lookingForDashes = false

      // Sum the number of characters we looked at
      val checkedCharacters = dashesOnFirstLine + nonDashesOnFirstLine

      // Verify that the number of dashes will not cause a problem for the
      // BLAST+ CLI tool by applying the same logic the CLI tool does to
      // validate the line.
      if (
        dashesOnFirstLine >= nonDashesOnFirstLine / 3
        && (checkedCharacters > 3 || nonDashesOnFirstLine == 0 || dashesOnFirstLine > nonDashesOnFirstLine)
      )
        throw TooManyDashesOnFirstLineException(sequenceCount)
    }
  }

  override fun onSequenceCharacter(c: Char) {
    if (isIgnorable(c))
      return

    if (!isValid(c))
      throwBadChar()

    if (lookingForDashes && columnIndex <= maxFirstLineCheckingChars) {
      if (c == '-')
        dashesOnFirstLine++
      else
        nonDashesOnFirstLine++
    }

    currentSequenceLength++
    totalQueryLength++

    if (totalQueryLength > maxTotalQueryLength)
      throw QueryTooLongException(maxTotalQueryLength)
  }

  override fun close() {
    stream.close()
  }

  private fun throwBadChar(): Nothing =
    throw InvalidSequenceCharacterException(sequenceType, sequenceCount, lineNumber, columnIndex, currentChar)
}
