package mb.query.parse

import java.io.BufferedReader
import java.io.InputStream
import java.io.Reader

private const val DefLineLeader = '>'

/**
 * Multi-Sequence Query Parser
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since v2.0.0
 *
 * @constructor Constructs a new multi-sequence query parser.
 *
 * @param maxSequences Maximum number of sequences allowed in a single
 * multi-sequence query.
 *
 * @param maxSequenceSize Maximum number of non-ignorable characters that may
 * appear in a single query sequence (not counting the def line).
 */
abstract class QueryParser(
  private val maxSequences: Int,
  private val maxSequenceSize: Int,
) {

  abstract val sequenceType: String

  private var sequenceNumber = 1
  private var lineNumber = 0
  private var columnNumber = 0

  /**
   * Counter for the number of non-ignorable characters that appear in the
   * sequence currently being processed.
   */
  private var charsInSequence = 0

  /**
   * Defline for the sequence currently being processed.
   */
  private var currentDefline: String? = null

  /**
   * Buffer for the characters in the sequence currently being processed.
   */
  private val sequenceBuffer = StringBuilder(4096)

  /**
   * List of the sequences that we've parsed so far.
   */
  private val sequences = ArrayList<BlastSequence>(15)

  /**
   * Validates and parses the given multi-sequence query into an instance of
   * [MBlastQuery] which contains entries for all the sequences that appear in
   * the input.
   *
   * @param query Multi-sequence query to parse.
   *
   * @return MBlastQuery instance wrapping the parsed query sequences.
   *
   * @throws MBlastQueryParseException If a validation error occurs while
   * attempting to validate and parse the input query.
   */
  fun parseQuery(query: String) = parseQuery(query.reader())

  /**
   * Validates and parses the given multi-sequence query stream into an instance
   * of [MBlastQuery] which contains entries for all the sequences that appear
   * in the input.
   *
   * @param stream Stream containing the multi-sequence query to parse.
   *
   * @return MBlastQuery instance wrapping the parsed query sequences.
   *
   * @throws MBlastQueryParseException If a validation error occurs while
   * attempting to validate and parse the input query.
   */
  fun parseQuery(stream: InputStream) = parseQuery(stream.bufferedReader())

  /**
   * Validates and parses the given multi-sequence query stream into an instance
   * of [MBlastQuery] which contains entries for all the sequences that appear
   * in the input.
   *
   * @param stream Stream containing the multi-sequence query to parse.
   *
   * @return MBlastQuery instance wrapping the parsed query sequences.
   *
   * @throws MBlastQueryParseException If a validation error occurs while
   * attempting to validate and parse the input query.
   */
  fun parseQuery(stream: Reader) = parseQuery(stream.buffered())

  /**
   * Tests whether the given character is valid for the implementation sequence
   * type.
   *
   * @param char Character to test.
   *
   * @return `true` if the character is valid for the implementing sequence
   * type, otherwise `false`.
   */
  protected abstract fun isCharValid(char: Char): Boolean

  /**
   * Tests whether the given character is "ignorable", meaning it will be passed
   * through without being validated or counting towards the sequence size.
   *
   * By default, whitespace and digit characters are ignored.
   *
   * @param char Character to test.
   *
   * @return `true` if the character is ignorable, otherwise `false`.
   */
  protected open fun isCharIgnorable(char: Char): Boolean {
    return char.isWhitespace() || char.isDigit()
  }

  /**
   * Internal query parse.
   */
  private fun parseQuery(stream: BufferedReader): MBlastQuery {
    var line = stream.readLine()

    while (line != null) {
      lineNumber++
      columnNumber = 0

      handleLine(line)

      line = stream.readLine()
    }

    // We have finished processing the input query.

    // We _should_ have some stuff in the buffer for the final sequence in the
    // input query.
    if (sequenceBuffer.isNotBlank()) {

      // We will have appended one extra newline to the end of the buffer due to
      // the way the [handleLine] method works.  Clip it off.
      sequenceBuffer.setLength(sequenceBuffer.length - 1)

      // We _did_ have some stuff in the buffer.
      // Finalize the sequence
      finalizeSequence()

      // And return the set of sequences that we parsed.
      return MBlastQuery(sequences.toList())
    }

    // If we made it here then either there were no sequences, or the final
    // sequence in the query was blank.  Either way, it's an error, so throw it.
    throw EmptySequenceException(sequenceNumber, lineNumber)
  }

  private fun handleLine(line: String) {
    // If the entire line is blank:
    if (line.isEmpty()) {
      // Append an empty line to the sequence buffer and move onto the next
      // line.
      sequenceBuffer.appendLine()
      return
    }

    // If the first character in the line is a leader character for a defline:
    // (NOTE: the defline leader _MUST_ be at the beginning of the line, if we
    if (line[0] == DefLineLeader) {

      // If we have gathered no characters for a previous sequence:
      if (charsInSequence == 0) {

        // If we are on the first sequence:
        if (sequenceNumber == 1) {

          // Then we are starting the whole multiquery off with a sequence that
          // has a defline.
          currentDefline = line

          // Clear out any leading blank space from the buffer
          sequenceBuffer.clear()

          // And since it was a defline, we are done processing it.
          return
        }

        // Else, if we are _not_ on the first sequence, then there were 2
        // deflines in a row, meaning an empty sequence.

        throw EmptySequenceException(sequenceNumber, lineNumber)
      }

      // Else, if we _have_ gathered at least one non-ignorable character in the
      // sequence buffer:

      // Save the previous sequence
      finalizeSequence()

      // Set the defline for the next sequence
      currentDefline = line

      // Since it was a defline, we are done processing it.
      return
    }

    // Else if the first character of the line was NOT a leader character for
    // a defline, we will be parsing it as a line of sequence characters.

    // Iterate through the characters in the line and append them to the buffer.
    line.forEach { appendChar(it) }

    // Keep the user's trailing newline
    sequenceBuffer.appendLine()
  }

  /**
   * Attempts to append the given character to the current sequence buffer, if
   * the character is valid.
   *
   * If the character is ignorable, it will not be validated and will not count
   * against the sequence's size cap.
   *
   * If the character is not ignorable, it will be validated and will count
   * against the sequence's size cap.
   *
   * @param char Character to append.
   *
   * @throws InvalidSequenceCharacterException If the given character is not
   * valid for the implementation sequence type.
   */
  private fun appendChar(char: Char) {
    // If the current character is not "ignorable" (see "isCharIgnorable(char)):
    if (!isCharIgnorable(char)) {

      // The current character is not ignorable, so we will validate it and
      // count it towards the total sequence size.
      if (!isCharValid(char))
        throw InvalidSequenceCharacterException(sequenceType, sequenceNumber, lineNumber, columnNumber, char)

      charsInSequence++

      // If we have gone over the max number of non-ignorable characters allowed
      // in a single sequences:
      if (charsInSequence-1 > maxSequenceSize)
        throw SequenceTooLongException(sequenceType, sequenceNumber, maxSequenceSize)
    }

    sequenceBuffer.append(char)
  }

  /**
   * Finalizes and appends the sequence that is currently being built and resets
   * the internal state to continue parsing more sequences.
   */
  private fun finalizeSequence() {
    // Record the current sequence state as a new sequence entry.
    sequences.add(BlastSequence(currentDefline, sequenceBuffer.toString()))

    // Increment the sequence counter
    sequenceNumber++

    // If we have gone over the max number of sequences allowed for a single
    // query:
    if (sequenceNumber-1 > maxSequences)
      throw TooManySequencesException(maxSequences)

    // Reset the state for the next sequence
    currentDefline = null
    sequenceBuffer.clear()
    charsInSequence = 0
  }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// TEST INPUTS
// - leading lines and whitespaces before the first defline (should be trimmed out)
// - blank lines in between the defline and the sequence start
// - blank lines in the middle of a sequence
// - blank lines trailing a sequence