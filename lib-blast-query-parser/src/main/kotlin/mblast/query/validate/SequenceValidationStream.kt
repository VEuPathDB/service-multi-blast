package mblast.query.validate

import mblast.query.SequenceStateStream
import mblast.query.util.isIgnorable
import mblast.util.io.InStream

/**
 * # BLAST Multi-Sequence Query Validation Stream
 *
 * A stream wrapper that performs validations as defined below on a BLAST query
 * of one or more sequences as they pass through.
 *
 * As this type is a stream wrapper that itself implements a streaming API, the
 * stream will need to be fully or partially consumed for any validation to be
 * performed.
 *
 * TODO: Allow for comment lines!
 *
 * ## Validations
 *
 * ### First Line Dashes
 *
 * Validates that the first line of the sequence does not contain too many
 * dashes.  "Too many" here is calculated as any value for `dashCount` that is
 * greater than or equal to `nonDashCount / 3`
 *
 * ```
 * if (dashCount >= nonDashCount / 3)
 *   ERROR: First line of the sequence does not look like plausible data, but it
 *          is not marked as a defline or comment.
 * else
 *   OK:    First line has an acceptable ratio of dashes to non-dashes
 * ```
 *
 * This check is based on the C++ source code of the CFastaReader type as of
 * 2022-12-13, specifically the method `CFastaReader::CheckDataLine`.
 *
 * The relevant snippets are:
 *
 * Definition of how many characters max to check on the first line:
 * ```
 * // in case the data has huge sequences all on the first line we do need
 * // a cutoff and "70" seems reasonable since it's the default width of
 * // CFastaOstream (as of 2017-03-09)
 * size_t len_to_check = min(s.length(),
 *                           static_cast<size_t>(70));
 * ```
 *
 * Definition of the line content validation ("bad" here is invalid or dash
 * characters):
 * ```
 * if (bad >= good / 3  &&
 *     (len_to_check > 3  ||  good == 0  ||  bad > good))
 * {
 *     FASTA_ERROR( LineNumber(),
 *         "CFastaReader: Near line " << LineNumber()
 *         << ", there's a line that doesn't look like plausible data, "
 *         "but it's not marked as defline or comment.",
 *         CObjReaderParseException::eFormat);
 * }
 * ```
 *
 * ### Sequence Length
 *
 * The stream will test the count of non-ignorable sequence characters in each
 * sequence against the configured [maxSequenceLength] value.  If any individual
 * sequence is found to have more non-ignorable characters than that limit,
 * a [SequenceTooLongException] will be thrown.
 *
 * Additionally, if any sequence in the query is found to have `0` total
 * non-ignorable characters an [EmptySequenceException] will be thrown.
 *
 * ### Total Query Length
 *
 * The stream will test the total count of non-ignorable sequence characters
 * across all sequences in the stream against the configured
 * [maxTotalQueryLength] value.  If the total number of non-ignorable characters
 * read exceeds that value, a [QueryTooLongException] will be thrown.
 *
 * ### Total Sequence Count
 *
 * The stream will count the number of sequences passing through.  If the total
 * count of sequences is greater than the configured [maxSequenceCount] value,
 * a [TooManySequencesException] will be thrown.
 *
 * ### Invalid Sequence Characters
 *
 * Implementations this class are tasked with implementing their own character
 * validation, however if any non-ignorable character in any sequence is found
 * to not be valid by the rules the implementation defines, a
 * [InvalidSequenceCharacterException] will be thrown.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 *
 * @constructor Constructs a new `SequenceValidationStream` instance.
 *
 * @param maxSequenceLength The maximum allowed count of non-ignorable
 * characters in an individual sequence.
 *
 * @param maxSequenceCount The maximum allowed count of total sequences in a
 * multi-query stream.
 *
 * @param maxTotalQueryLength The maximum allowed count of non-ignorable
 * characters in a multi-query stream.
 *
 * @param stream Source stream from which the query will be read.
 */
abstract class SequenceValidationStream(
  private val maxSequenceLength: Int,
  private val maxSequenceCount: Int,
  private val maxTotalQueryLength: Int,
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
  private var totalQueryLength = 0

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
    if (totalQueryLength == 0)
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

    // If the input query has too many sequences, throw an exception.
    if (sequenceCount > maxSequenceCount)
      throw TooManySequencesException(maxSequenceCount)

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

    if (currentSequenceLength > maxSequenceLength)
      throw SequenceTooLongException(sequenceType, sequenceCount, currentSequenceStartLineNumber, maxSequenceLength)

    if (totalQueryLength > maxTotalQueryLength)
      throw QueryTooLongException(maxTotalQueryLength)
  }

  override fun close() {
    stream.close()
  }

  private fun throwBadChar(): Nothing =
    throw InvalidSequenceCharacterException(sequenceType, sequenceCount, lineNumber, columnIndex, currentChar)
}