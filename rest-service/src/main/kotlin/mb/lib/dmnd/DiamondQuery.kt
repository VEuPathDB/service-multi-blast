package mb.lib.dmnd


import mb.api.service.valid.SequenceDefLineValidationError
import mb.api.service.valid.SequenceEmptyValidationError
import mb.api.service.valid.SequenceValidationError
import mb.lib.dmnd.DiamondQuery.Companion.fromStream
import mb.lib.dmnd.DiamondQuery.Companion.fromString
import mb.lib.query.AbstractMBlastQuery
import mb.lib.query.MBlastQuery
import mb.lib.util.isWhitespace
import org.veupathdb.lib.cli.diamond.DiamondCommand
import java.io.InputStream
import java.nio.charset.StandardCharsets

/**
 * Represents a blast query consisting of 1 or more sequences.
 *
 * @constructor Constructs a new [DiamondQuery] instance.
 *
 * @param tool Target BLAST+ tool this query is for.
 *
 * @param sequences A list of one or more sequences making up this query.
 *
 * @see [fromString]
 * @see [fromStream]
 */
internal data class DiamondQuery(val tool: DiamondCommand, override val sequences: List<DiamondSubQuery>)
  : MBlastQuery
  , AbstractMBlastQuery()
{
  /**
   * Validates all the sequences in this query.
   */
  override fun validate(): SequenceValidationError? {
    // If we don't have any sequences
    if (sequences.isEmpty())
      return SequenceEmptyValidationError()

    var seq = 1

    for (sub in sequences) {
      val err = sub.validate(seq)
      if (err != null)
        return err
      seq++
    }

    return null
  }

  companion object {
    /**
     * Parses the given input string into a series of sequences and creates a
     * new [DiamondQuery] instance with those sequences.
     */
    @JvmStatic
    fun fromString(tool: DiamondCommand, input: String): DiamondQuery {
      val subQueries = ArrayList<DiamondSubQuery>()
      val buffer     = StringBuilder()
      var index      = 1
      var lineNum    = 0

      val badDefLines = mutableListOf<Int>()

      // Current defline
      //
      // This will hold the defline value for the subsequence we are currently
      // reading through.
      //
      // If there is only one sequence in the query, the defline is optional.
      // If there are multiple sequences in the query, each sequence will be
      // preceded by a defline.
      var currentHeader: String? = null

      // Iterate through the lines in the input query text
      for (line in input.lineSequence()) {
        lineNum++

        // If the current line is a defline (starts with '>')
        if (line.startsWith(">")) {

          // DIAMOND SPECIFIC: Disallow absent/blank sequence IDs
          if (line.length == 1 || line[1].isWhitespace) {
            badDefLines.add(lineNum)
          }

          // AND we already had a current defline for the current sequence
          if (currentHeader != null) {

            // Then we are starting a new sequence in the query.  Close out the
            // sequence we were working on so we can start a new one.
            subQueries.add(DiamondSubQuery(
              index++,
              tool,
              currentHeader,
              buffer.toString()
            ))
            buffer.setLength(0)
          }

          // Set the current defline to the one we just encountered.
          currentHeader = line
        }

        // else, the line is not a defline
        else {
          // then it must be part of a sequence, add it to the sequence buffer.
          buffer.append(line).append("\n")
        }
      }

      // We've reached the end of the input query, close out the last sequence
      // we were working on.
      if (buffer.isNotEmpty()) {
        subQueries.add(DiamondSubQuery(
          index,
          tool,
          currentHeader,
          buffer.toString()
        ))
      }

      if (badDefLines.isNotEmpty()) {
        throw SequenceDefLineValidationError(badDefLines)
      }

      return DiamondQuery(tool, subQueries)
    }

    /**
     * Parses the contents of the given [InputStream] into a series of sequences
     * and creates a new [DiamondQuery] instance with those sequences.
     */
    @JvmStatic
    fun fromStream(tool: DiamondCommand, input: InputStream) =
      fromString(tool, String(input.readAllBytes(), StandardCharsets.UTF_8))
  }
}

