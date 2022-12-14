package mblast.query

import mblast.query.util.NUL
import mblast.query.util.isNewLine
import mblast.query.util.isWhitespace

private const val STATE_START_QUERY: Byte = 0
private const val STATE_START_LINE:  Byte = 1
private const val STATE_IN_SEQUENCE: Byte = 2
private const val STATE_IN_DEF_LINE: Byte = 3

private const val DEF_LINE_LEADER = '>'

/**
 * # Headless Sequence Parsing State Machine
 *
 * A simple state machine that reads through a BLAST+ query containing one or
 * more sequences and calls overridable extension points for each of the
 * following states or events that appear in the input:
 *
 * * Process start
 * * Hit sequence start
 * * Hit def line start
 * * Hit def line character
 * * Hit sequence body start
 * * Hit sequence body character
 * * Process finished
 *
 * Additionally, this class tracks various details about the query being parsed
 * to assist in implementing processes around a query stream.
 *
 * This type is "headless" in that it does not contain any methods to drive the
 * process and relies on extending classes to make use of its internals to
 * implement query stream processing.
 *
 * @see SequenceStateStream
 * @see SequenceStateRunnable
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 */
abstract class HeadlessSequenceStateMachine {

  /**
   * Current state of this object.
   */
  private var state = STATE_START_QUERY

  /**
   * Current character being processed from the input.
   */
  protected var currentChar = NUL
    private set

  /**
   * Previous character processed from the input.
   */
  protected var lastChar = NUL
    private set

  /**
   * Count of raw characters that have been processed since the start of the
   * query or most recent sequence.
   *
   * This count includes newlines, whitespaces, and other ignorable characters.
   */
  protected var rawCharactersThisSequence: Int = 0
    private set

  /**
   * Index of the current sequence in the overall query input.
   */
  protected var sequenceCount: Int = 0
    private set

  /**
   * Count of lines seen in the overall input.
   */
  protected var lineNumber: Int = 1
    private set

  /**
   * Line number of the start of the current sequence.
   */
  protected var currentSequenceStartLineNumber: Int = 1
    private set

  /**
   * Cursor position in the current line.
   *
   * This value is 1 based.
   */
  protected var columnIndex: Int = 1
    private set

  ////////////////////////////////////////////////////////////////////////////
  //                                                                        //
  //  Extension Points                                                      //
  //                                                                        //
  ////////////////////////////////////////////////////////////////////////////

  /**
   * Fetches the next character from the input query.
   *
   * If there are no more characters remaining, returns `-1`.
   *
   * @return The next character in the input query, or `-1` if the end of the
   * input has been reached.
   */
  protected abstract fun nextCharacter(): Int

  /**
   * Extension point that is to be called as soon as the stream processing
   * starts, before any characters have been consumed.
   */
  protected open fun onQueryStart() {}

  /**
   * Extension point that is called after all processing has completed.
   */
  protected open fun onQueryComplete() {}

  /**
   * Extension point that is called when a new def line is started.
   */
  protected open fun onDefLineStart() {}

  /**
   * This method will be called with every non-leader character that is part of
   * a defline.
   */
  protected open fun onDefLineCharacter(c: Char) {}

  /**
   * Extension point that is called when a new sequence is started.
   */
  protected open fun onSequenceStart() {}

  protected open fun onSequenceBodyStart() {}

  /**
   * This method will be called with every non-newline character that is part of
   * a sequence (not including def lines).
   */
  protected open fun onSequenceCharacter(c: Char) {}

  /**
   * Extension point that is called when the end of a sequence line is reached.
   *
   * This method is called "on" the line that is ending, meaning the line number
   * values will be for the line that is ending.
   */
  protected open fun onSequenceLineEnd() {}

  /**
   * Extension point that is called before parsing the first sequence character
   * of a new sequence line.
   *
   * This method is called "on" the new line, meaning the line number values
   * will be for the new line that is being started.
   */
  protected open fun onSequenceLineBegin() {}


  ////////////////////////////////////////////////////////////////////////////
  //                                                                        //
  //  Class Internals                                                       //
  //                                                                        //
  ////////////////////////////////////////////////////////////////////////////


  /**
   * Sets the [currentChar] property to the given value and the [lastChar]
   * property to the previous value of `currentChar`.
   *
   * @param char New current character value.
   */
  protected fun shiftCurrentChar(char: Char) {
    lastChar = currentChar
    currentChar = char
  }

  /**
   * Processes the current character.
   */
  protected fun index() {
    if (state != STATE_IN_DEF_LINE)
      rawCharactersThisSequence++

    when (state) {
      STATE_IN_SEQUENCE -> internalHandleSequenceContinuation()
      STATE_IN_DEF_LINE -> internalHandleDefLineContinuation()
      STATE_START_LINE  -> internalHandleLineStart()
      STATE_START_QUERY -> internalHandleQueryStart()
    }

    columnIndex++
  }

  /**
   * Handles the state where we are beginning a new line in an already started
   * sequence.
   */
  private fun internalHandleSequenceNextLine() {
    state = STATE_IN_SEQUENCE
    onSequenceLineBegin()
    internalHandleSequenceContinuation()
  }

  /**
   * Handles the state where we are in a sequence and processing the next
   * character.
   */
  private fun internalHandleSequenceContinuation() {
    if (isNewLine(currentChar)) {
      state = STATE_START_LINE
      onSequenceLineEnd()
    } else {
      onSequenceCharacter(currentChar)
    }
  }

  /**
   * Handles the state where we are in a defline and processing the next
   * character.
   */
  private fun internalHandleDefLineContinuation() {
    if (isNewLine(currentChar))
      state = STATE_START_LINE
    else
      onDefLineCharacter(currentChar)
  }

  /**
   * Handles the state where we are starting a new line and need to determine
   * whether it is a defline, a blank line, the beginning of a sequence, or the
   * continuation of an already started sequence.
   */
  private fun internalHandleLineStart() {
    incrementLineNumber()
    when {
      // Blank line, keep the same state and continue.
      isNewLine(currentChar)         -> {}

      // If the character is a def-line leader character, then update the state
      // and do nothing else, we don't pass this character to the extension point
      // methods.
      currentChar == DEF_LINE_LEADER -> internalHandleDefLineStart()

      // If the character was not a def line leader character, then we are in
      // sequence town.  If we already have sequence characters counted then we
      // are in the middle of a sequence, otherwise we are starting a new one.
      else                           ->
        if (rawCharactersThisSequence > 0) {
          internalHandleSequenceNextLine()
        } else {
          internalHandleSequenceBodyStart()
        }
    }
  }

  private fun internalHandleSequenceBodyStart() {
    onSequenceBodyStart()
    onSequenceLineBegin()
    state = STATE_IN_SEQUENCE
    internalHandleSequenceContinuation()
  }

  /**
   * Handler for the first character in a def line, which is always a '>'
   * character.
   */
  private fun internalHandleDefLineStart() {
    incrementSequenceIndex()
    onSequenceStart()
    onDefLineStart()
    state = STATE_IN_DEF_LINE
    // Do not call anything else because we just pass the def line leader
    // character through untouched.
  }

  private fun internalHandleHeadlessSequenceStart() {
    incrementSequenceIndex()
    onSequenceStart()
    internalHandleSequenceBodyStart()
  }

  /**
   * Internal handler for the start of an input query.
   *
   * This state is different from the rest in that it doesn't change when a new
   * line is encountered.  The state will only change from [STATE_START_QUERY]
   * when a sequence or def line is encountered.
   */
  private fun internalHandleQueryStart() {
    when {
      // As a special case, we don't update the state for newlines in here due
      // to the fact that we don't know if the first sequence in the query has
      // a leading def line or not.  This means that newlines encountered in
      // this state may be "part of" a sequence, or it may just be junk before
      // the first def line.
      isNewLine(currentChar)         -> incrementLineNumber()

      // Similar to newlines, this may be junk, or it may be "part of" a
      // sequence that we haven't seen the first real character for yet.
      isWhitespace(currentChar)      -> {}

      // We've hit a def line leader character... this is a bit wonky, but this
      // doesn't actually mean we've hit a valid def line, if there are spaces
      // before this character in the line, then it is _not_ valid, and we will
      // count it as part of a sequence (an invalid sequence).
      currentChar == DEF_LINE_LEADER -> {
        if (columnIndex == 1) {
          internalHandleDefLineStart()
        } else {
          internalHandleHeadlessSequenceStart()
        }
      }

      // Else it's part of a sequence, valid or not.
      else                           -> internalHandleHeadlessSequenceStart()
    }
  }

  private fun incrementLineNumber() {
    lineNumber++
    // We set this to zero here because the end of the [index] method will
    // increment the value to one for us after this call.
    columnIndex = 0
  }

  private fun incrementSequenceIndex() {
    sequenceCount++
    rawCharactersThisSequence = 0
    currentSequenceStartLineNumber = lineNumber
  }
}