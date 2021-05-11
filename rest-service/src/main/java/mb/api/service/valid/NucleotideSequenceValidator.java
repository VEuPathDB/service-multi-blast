package mb.api.service.valid;

import mb.lib.config.Config;

/**
 * Validates that a given character or sequence falls within the legal character
 * set for nucleotide sequences.
 * <p>
 * Legal character set:
 * <pre>
 * ABCDGHKMNRSTUVWY-
 * abcdghkmnrstuvwy
 * </pre>
 */
public class NucleotideSequenceValidator implements SequenceValidator
{
  private static final Config conf = Config.getInstance();

  @Override
  public String kind() {
    return "nucleotide";
  }

  @Override
  public boolean isValid(int b) {
    if (b < '-' || b > 'z')
      return false;

    return isValid((byte) b);
  }

  @Override
  public boolean isValid(char b) {
    if (b < '-' || b > 'z')
      return false;

    return isValid((byte) b);
  }

  @Override
  public boolean isValid(byte b) {
    return ('A' <= b && b <= 'D')
      || ('G' <= b && b <= 'H')
      || b == 'K'
      || ('M' <= b && b <= 'N')
      || ('R' <= b && b <= 'W')
      || b == 'Y'
      || b == '-'
      || ('a' <= b && b <= 'd')
      || ('g' <= b && b <= 'h')
      || b == 'k'
      || ('m' <= b && b <= 'n')
      || ('r' <= b && b <= 'w')
      || b == 'y';
  }

  @Override
  public boolean isValidLength(int len) {
    return len <= conf.getMaxNASeqSize();
  }

  @Override
  public int maxSeqLength() {
    return conf.getMaxNASeqSize();
  }
}
