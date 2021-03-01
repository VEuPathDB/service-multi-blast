package org.veupathdb.service.multiblast.service.valid;

import mb.lib.config.Config;

public interface SequenceValidator
{
  String kind();

  boolean isValid(int c);

  boolean isValid(char c);

  boolean isValid(byte c);

  default boolean isValidLength(CharSequence seq) {
    return isValidLength(seq.length());
  }

  boolean isValidLength(int seqLen);

  int maxSeqLength();

  default SequenceValidationError validate(CharSequence seq) {
    for (var i = 0; i < seq.length(); i++) {
      if (!isValid(seq.charAt(i))) {
        return new SequenceValidationError(i+1, seq.charAt(i));
      }
    }

    return null;
  }

  default SequenceValidationError validate(char[] chars) {
    for (var i = 0; i < chars.length; i++) {
      if (!isValid(chars[i])) {
        return new SequenceValidationError(i+1, chars[i]);
      }
    }

    return null;
  }

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
