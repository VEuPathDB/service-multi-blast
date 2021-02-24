package org.veupathdb.service.multiblast.service.valid;

// Legal characters:
//   ABCDGHKMNRSTUVWY-
//   abcdghkmnrstuvwy-
public class NucleicSequenceValidator implements SequenceValidator
{
  @Override
  public boolean isValid(CharSequence seq) {
    return seq.chars().allMatch(this::isValid);
  }

  @Override
  public boolean isValid(char[] chars) {
    for (var c : chars)
      if (!isValid(c))
        return false;

    return true;
  }

  @Override
  public boolean isValid(byte[] bytes) {
    for (var b : bytes)
      if (!isValid(b))
        return false;

    return true;
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
}
