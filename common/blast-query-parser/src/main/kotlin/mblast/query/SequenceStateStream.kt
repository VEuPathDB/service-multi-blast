package mblast.query

import mblast.query.util.EOF
import mblast.query.util.NUL
import mblast.util.io.InStream

abstract class SequenceStateStream : HeadlessSequenceStateMachine(), InStream {

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