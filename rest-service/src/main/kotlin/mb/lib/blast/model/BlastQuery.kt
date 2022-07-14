package mb.lib.blast.model


import mb.api.service.valid.SequenceEmptyValidationError
import mb.api.service.valid.SequenceValidationError
import mb.api.service.valid.SequenceValidator
import org.veupathdb.lib.blast.BlastTool
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*
import java.util.regex.Pattern
import java.util.stream.Collectors

/**
 * Represents a blast query consisting of 1 or more sequences.
 *
 * @constructor Constructs a new [BlastQuery] instance.
 *
 * @param tool Target BLAST+ tool this query is for.
 *
 * @param sequences A list of one or more sequences making up this query.
 *
 * @see [fromString]
 * @see [fromStream]
 */
data class BlastQuery(val tool: BlastTool, val sequences: List<BlastSubQuery>) {

  /**
   * Concatenates all the sequences in this query into a single query string.
   */
  fun getFullQuery(): String =
    sequences.stream()
      .map(BlastSubQuery::toStandardString)
      .collect(Collectors.joining("\n"))

  /**
   * Validates all the sequences in this query.
   */
  fun validate(): SequenceValidationError? {
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
     * new [BlastQuery] instance with those sequences.
     */
    @JvmStatic
    fun fromString(tool: BlastTool, input: String): BlastQuery {
      val scanner    = Scanner(input)
      val subQueries = ArrayList<BlastSubQuery>()
      val buffer     = StringBuilder()
      var index      = 1

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
      while (scanner.hasNextLine()) {
        val line = scanner.nextLine()

        // If the current line is a defline (starts with '>')
        if (line.startsWith(">")) {

          // AND we already had a current defline for the current sequence
          if (currentHeader != null) {

            // Then we are starting a new sequence in the query.  Close out the
            // sequence we were working on so we can start a new one.
            subQueries.add(BlastSubQuery(
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
        subQueries.add(BlastSubQuery(
          index,
          tool,
          currentHeader,
          buffer.toString()
        ))
      }

      return BlastQuery(tool, subQueries)
    }

    /**
     * Parses the contents of the given [InputStream] into a series of sequences
     * and creates a new [BlastQuery] instance with those sequences.
     */
    @JvmStatic
    fun fromStream(tool: BlastTool, input: InputStream) =
      fromString(tool, String(input.readAllBytes(), StandardCharsets.UTF_8))
  }
}

/**
 * Represents a single sequence in a multi-blast query.
 *
 * @param index Index of this sequence in the parent query.
 *
 * @param tool Target BLAST+ tool for this sequence.
 *
 * @param defLine Sequence definition line.
 *
 * @param sequence Raw, unformatted sequence from the client input.
 */
class BlastSubQuery(
  val index: Int,
  val tool: BlastTool,
  val defLine: String?,
  sequence: String
) {

  private companion object {
    val whitespace = Regex("[\\s\\n\\r]")
    val matcher  = Pattern.compile(".{1,80}")
  }

  val rawSequence: String

  init {
    rawSequence = sequence.trim()
  }

  fun validate(sequenceNum: Int) =
    SequenceValidator.getValidator(tool).validate(sequenceNum, rawSequence)

  fun toStandardString(): String {
    val seq = rawSequence.replace(whitespace, "")
    val out = StringBuilder(seq.length + (defLine?.length ?: 0) + 100)

    defLine?.run { out.append(this).append('\n') }

    Scanner(seq)
      .findAll(matcher)
      .map { it.group() }
      .forEach { out.append(it).append('\n') }

    return out.toString().trim()
  }

  override fun toString() = toStandardString()
}
