package mblast.lib.query

import io.foxcapades.lib.kps.kpd.CharDeque
import java.io.BufferedReader
import java.io.Reader

class CharStream {
  private val queries: MutableList<QuerySequence>

  val filter: SequenceCharFilter
  val validator: SequenceCharValidator

  private var queryNumber = 1
  private var lineNumber  = 0
  private var charIndex   = 0

  private val queryBuffer = StringBuilder(4096)
  private val lineBuffer  = CharDeque(80)

  private var defLine: String? = null

  fun iterateChars1(reader: Reader) {
    return iterateChars2(reader.buffered())
  }

  fun iterateChars2(reader: BufferedReader) {
    var line = reader.readLine()

    while (line != null) {
      lineNumber++
      charIndex = 0

      handleLine(line)
      line = reader.readLine()
    }
  }

  fun handleLine(line: String) {
    // Skip empty lines.
    if (line.isEmpty())
      return

    /* We hit a defline.  This means we are either at our first sequence and
       it happens to have a defline, or we are at a subsequent sequence.

       We will wrap up the previously started sequence (if it _was_ started)
       and set up to start recording a new sequence. */
    if (line[0] == '>') {
      // If we presently have some characters in our line buffer:
      if (lineBuffer.size > 0) {
        // push them to the full query buffer
        queryBuffer.appendLine()
        queryBuffer.append(lineBuffer)

        // zero out our line buffer
        lineBuffer.clear()
      }

      /* If there was a defline, but no query body, this is an error that we
         need to catch and report on. */
      if (defLine != null && queryBuffer.isEmpty()) {
        throw EmptyQueryException(lineNumber-1, queryNumber)
      }

      // If we presently have some characters in our query buffer:
      if (queryBuffer.isNotEmpty()) {
        // Copy our query buffer and previous def line into a new query
        // sequence instance.
        queries.add(QuerySeq(defLine, queryBuffer.toString()))
        queryNumber++

        // Clear the query buffer for the next query.
        queryBuffer.clear()
      }

      defLine = line
      return
    }


    for (char in line) {
      charIndex++

      handleQueryChar(char)
    }
  }

  fun handleQueryChar(char: Char) {
    // If the current character is not one we care about,
    if (!filter.include(char))
      // skip it.
      return

    // If the current character is not valid for the sequence type,
    if (!validator.isValid(char))
      // bail here
      throw InvalidQueryCharacterException(lineNumber, charIndex, queryNumber, char)

    lineBuffer.pushBack(char)
  }

  fun capitalize(char: Char) =
    if (char.code in 97 .. 122)
      char - 32
    else
      char
}