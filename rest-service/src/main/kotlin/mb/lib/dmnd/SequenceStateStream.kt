package mb.lib.dmnd

/**
 * BLAST Query State Machine Input Stream
 *
 * Stripped down from the original version in MBlast 2.0 for use with diamond
 * queries.
 */
abstract class SequenceStateStream : HeadlessSequenceStateMachine(), InStream {

  /**
   * Returns the next byte in the sequence or -1 if the end of the stream has
   * been reached.
   */
  override fun read(): Int {
    // If the current character is the null character, then we haven't read any
    // data yet, meaning we are at the beginning of the input.
    if (currentChar == NUL)
      onQueryStart()

    val raw = nextCharacter()

    if (raw > -1) {
      shiftCurrentChar(raw.toChar())

      index()
    } else {
      shiftCurrentChar(EOF)

      // Only call the onQueryComplete the first time they hit the end of the
      // stream.
      if (currentChar == EOF && lastChar != EOF)
        onQueryComplete()
    }

    return raw
  }
}
