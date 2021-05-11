package mb.api.service.valid;

public class SequenceValidationError
{
  private final int sequence;
  private final int  line;
  private final int  position;
  private final char character;

  public SequenceValidationError(int sequence, int line, int position, char character) {
    this.sequence  = sequence;
    this.line      = line;
    this.position  = position;
    this.character = character;
  }

  public int getSequence() {
    return sequence;
  }

  public int getLine() {
    return line;
  }

  public int getPosition() {
    return position;
  }

  public char getCharacter() {
    return character;
  }
}
