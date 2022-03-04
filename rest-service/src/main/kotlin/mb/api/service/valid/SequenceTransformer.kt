package mb.api.service.valid

import java.util.Scanner

/**
 * Max line length for a sequence line.
 */
private const val SequenceLineLength = 80

object Sequences {

  /**
   * Standardizes the input query into a query consisting of only the query
   * sequence.
   *
   * The output will have no blank lines, no extra whitespaces, and will be
   * broken into 80 character rows.
   *
   * Header lines will not be altered.
   *
   * Input:
   * ```
   * > Some really long header line containing a bunch of information that is more than 80 characters long.
   * aaaaaaaaaaaaaa
   * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
   *
   * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
   * a
   * ```
   *
   * Output
   * ```
   * > Some really long header line containing a bunch of information that is more than 80 characters long.
   * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
   * aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
   * aaaaaaaaaaaaaaaaaaaaa
   * ```
   */
  fun standardize(input: String): String {

    // Input sequence scanner.
    val scanner = Scanner(input)

    // Buffer used to standardize lines to a set number of characters.
    val lineBuffer = StringBuilder(SequenceLineLength)

    // Output writer.
    val writer = StringBuilder(input.length)

    // Segment of an input line that could not fit into the line buffer.
    var overflow: String

    while (scanner.hasNextLine()) {
      // Current line
      val line     = scanner.nextLine().trim()

      // If the line starts with a '>' character then it is a FASTA header line
      // and should be written to the output stream unchanged.
      if (line.startsWith('>')) {
        writer.appendLine(line)

        // If the line is empty, skip it, else write it out.
      } else if (line.isNotBlank()) {
        overflow = appendBuffer(lineBuffer, line)

        // If the overflow string is not empty, then the line buffer must be
        // full.
        while (overflow != "") {
          // Write the line buffer to the output stream.
          writer.appendLine(lineBuffer)
          lineBuffer.clear()
          // Attempt to fill the line buffer again until there are no characters
          // left in the overflow string.
          overflow = appendBuffer(lineBuffer, overflow)
        }

        // If the line buffer is greater than (should never happen) or equal to
        // the max sequence line length:
        if (lineBuffer.length >= SequenceLineLength) {
          writer.appendLine(lineBuffer)
          lineBuffer.clear()
        }
      }
    }

    if (lineBuffer.isNotEmpty())
      writer.appendLine(lineBuffer)

    return writer.toString()
  }

  private fun appendBuffer(buf: StringBuilder, line: String): String {
    // Buffer length
    val bln = buf.length
    // Input length
    val iln = line.length

    if (bln + iln <= SequenceLineLength) {
      buf.append(line)
      return ""
    }

    val substringLen = SequenceLineLength - bln

    buf.append(line.substring(0, substringLen))

    return line.substring(substringLen)
  }

}

