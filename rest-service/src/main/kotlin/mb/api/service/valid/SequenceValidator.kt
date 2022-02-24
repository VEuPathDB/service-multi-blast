package mb.api.service.valid

import org.veupathdb.lib.blast.BlastTool
import java.util.*

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
    val scan = Scanner(seq)
    var lineNum = 1
    var size = 0

    while (scan.hasNextLine()) {
      val line = scan.nextLine()

      if (!line.startsWith(">")) {
        for (i in line.indices) {
          size++
          if (!isValid(line[i])) {
            return SequenceCharacterValidationError(sequence, lineNum, i + 1, seq[i])
          }
        }
      }

      lineNum++
    }

    if (size > maxSeqLength)
      return SequenceLengthValidationError(size, maxSeqLength)
    return null
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