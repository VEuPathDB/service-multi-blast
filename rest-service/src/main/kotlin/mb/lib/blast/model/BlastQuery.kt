package mb.lib.blast.model


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

      var currentHeader: String? = null

      while (scanner.hasNextLine()) {
        val line = scanner.nextLine()

        if (line.startsWith(">")) {
          if (currentHeader != null) {
            subQueries.add(BlastSubQuery(
              index++,
              tool,
              currentHeader,
              buffer.toString()
            ))
            buffer.setLength(0)
          }

          currentHeader = line
        } else {
          buffer.append(line).append("\n")
        }
      }

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
