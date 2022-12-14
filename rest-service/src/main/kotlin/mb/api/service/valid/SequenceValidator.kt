package mb.api.service.valid

import org.veupathdb.lib.blast.BlastTool
import java.util.*
import kotlin.math.min

interface SequenceValidator {
  /**
   * Returns the kind of sequence the implementing SequenceValidator is
   * validating against.
   *
   * @return the kind of sequence being validating against.
   */
  val kind: String

  /**
   * Checks whether the given character is valid for the target sequence type.
   *
   * @param c Character to check.
   *
   * @return Whether the given character is valid.
   */
  fun isValid(c: Char): Boolean

  val maxSeqLength: Int

  /**
   * Validates the given {@code CharSequence} to confirm all characters are
   * within the legal character set for sequences of {@link #kind()}.
   *
   * @param seq Sequence to validate.
   *
   * @return Whether all characters in the input {@code CharSequence} are valid.
   */
  fun validate(sequence: Int, seq: String): SequenceValidationError? {
    validateFirstLine(sequence, seq)?.let { return it }

    // Input scanner
    val scan = Scanner(seq)
    // Current line number
    var lineNum = 1
    // Number of sequence lines in the input.
    var size = 0

    while (scan.hasNextLine()) {
      val line = scan.nextLine()

      if (!line.startsWith(">")) {
        for (i in line.indices) {
          size++
          // skip always numbers and space chars
          if (!line[i].isDigit() && !line[i].isWhitespace() && !isValid(line[i])) {
            return SequenceCharacterValidationError(sequence, lineNum, i + 1, seq[i])
          }
        }
      }

      lineNum++
    }

    // If the query had no sequence lines.
    if (size == 0)
      return SequenceEmptyValidationError()

    if (size > maxSeqLength)
      return SequenceLengthValidationError(size, maxSeqLength)

    return null
  }

  /**
   * Validates the first line in a sequence to ensure that it passes the first
   * line check performed by the BLAST+ CLI tool:
   * ```
   * if (bad >= good / 3  &&
   *   (len_to_check > 3  ||  good == 0  ||  bad > good))
   * {
   *   FASTA_ERROR( LineNumber(),
   *     "CFastaReader: Near line " << LineNumber()
   *     << ", there's a line that doesn't look like plausible data, "
   *     "but it's not marked as defline or comment.",
   *     CObjReaderParseException::eFormat);
   * }
   * ```
   */
  private fun validateFirstLine(sequence: Int, seq: String): SequenceValidationError? {
    val scan = Scanner(seq)

    while (scan.hasNextLine()) {
      val line = scan.nextLine()

      if (line.isNotBlank() && line[0] != '>') {
        var dashes    = 0
        var nonDashes = 0

        // Count the dashes + nondashes in the line
        for (i in 0 until min(line.length, 70)) {
          when (line[i]) {
            '-'  -> dashes++
            else -> nonDashes++
          }
        }

        val checked = dashes + nonDashes

        return if (
          dashes >= nonDashes / 3
          && (checked > 3 || nonDashes == 0 || dashes > nonDashes)
        )
          SequenceDashesValidationError(sequence)
        else
          null
      }
    }

    return SequenceEmptyValidationError()
  }

  companion object {
    fun getValidator(tool: BlastTool): SequenceValidator {
      return when (tool) {
        BlastTool.BlastP, BlastTool.TBlastN -> ProteinSequenceValidator
        else                                -> NucleotideSequenceValidator
      }
    }
  }

}
