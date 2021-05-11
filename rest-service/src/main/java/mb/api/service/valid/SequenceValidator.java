package mb.api.service.valid;

import java.util.Scanner;

import org.veupathdb.lib.blast.BlastTool;

public interface SequenceValidator
{
  /**
   * Returns the kind of sequence the implementing SequenceValidator is
   * validating against.
   *
   * @return the kind of sequence being validating against.
   */
  String kind();

  /**
   * Checks whether the given int character is valid for the target sequence
   * type.
   *
   * @param c Character to check.
   *
   * @return Whether the given character is valid.
   */
  boolean isValid(int c);

  /**
   * Checks whether the given character is valid for the target sequence type.
   *
   * @param c Character to check.
   *
   * @return Whether the given character is valid.
   */
  boolean isValid(char c);

  /**
   * Checks whether the given character is valid for the target sequence type.
   *
   * @param c Character to check.
   *
   * @return Whether the given character is valid.
   */
  boolean isValid(byte c);

  default boolean isValidLength(CharSequence seq) {
    return isValidLength(seq.length());
  }

  boolean isValidLength(int seqLen);

  int maxSeqLength();

  /**
   * Validates the given {@code CharSequence} to confirm all characters are
   * within the legal character set for sequences of {@link #kind()}.
   *
   * @param seq Sequence to validate.
   *
   * @return Whether all characters in the input {@code CharSequence} are valid.
   */
  default SequenceValidationError validate(int sequence, String seq) {
    var scan    = new Scanner(seq);
    var lineNum = 1;

    while (scan.hasNextLine()) {
      var line = scan.nextLine();

      if (!line.startsWith(">")) {
        for (var i = 0; i < line.length(); i++) {
          if (!isValid(line.charAt(i))) {
            return new SequenceValidationError(sequence, lineNum, i+1, seq.charAt(i));
          }
        }
      }

      lineNum++;
    }

    return null;
  }

  static SequenceValidator getValidator(BlastTool tool) {
    if (tool == null) // Assume blastn
      return new NucleotideSequenceValidator();

    return switch(tool) {
      case BlastP, TBlastN -> new ProteinSequenceValidator();
      default -> new NucleotideSequenceValidator();
    };
  }

}
