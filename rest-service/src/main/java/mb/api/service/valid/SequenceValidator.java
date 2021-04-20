package mb.api.service.valid;

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
  default SequenceValidationError validate(CharSequence seq) {
    for (var i = 0; i < seq.length(); i++) {
      if (!isValid(seq.charAt(i))) {
        return new SequenceValidationError(i+1, seq.charAt(i));
      }
    }

    return null;
  }

  /**
   * Validates the given char array to confirm all characters are within the
   * legal character set for sequences of {@link #kind()}.
   *
   * @param chars Array of characters to validate.
   *
   * @return Whether all characters in the input array are valid.
   */
  default SequenceValidationError validate(char[] chars) {
    for (var i = 0; i < chars.length; i++) {
      if (!isValid(chars[i])) {
        return new SequenceValidationError(i+1, chars[i]);
      }
    }

    return null;
  }

  /**
   * Validates the given byte array to confirm all characters are within the
   * legal character set for sequences of {@link #kind()}.
   *
   * @param bytes Array of bytes to validate.
   *
   * @return Whether all characters in the input array are valid.
   */
  default SequenceValidationError validate(byte[] bytes) {
    for (var i = 0; i < bytes.length; i++) {
      if (!isValid(bytes[i])) {
        return new SequenceValidationError(i+1, (char) bytes[i]);
      }
    }

    return null;
  }

  class SequenceValidationError
  {
    private final int  linePosition;
    private final char character;

    public SequenceValidationError(int linePosition, char character) {
      this.linePosition = linePosition;
      this.character    = character;
    }

    public int getLinePosition() {
      return linePosition;
    }

    public char getCharacter() {
      return character;
    }
  }
}
