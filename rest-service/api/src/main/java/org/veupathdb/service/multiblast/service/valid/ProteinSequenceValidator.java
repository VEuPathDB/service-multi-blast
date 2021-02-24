package org.veupathdb.service.multiblast.service.valid;

// Legal Characters
//   ABCDEFGHIKLMNPQRSTUVWXYZ*-
//   abcdefghiklmnpqrstuvwxyz*-
public class ProteinSequenceValidator implements SequenceValidator
{
  @Override
  public boolean isValid(int c) {
    if (c < '-' || c > 'z')
      return false;

    return isValid((byte) c);
  }

  @Override
  public boolean isValid(char c) {
    if (c < '-' || c > 'z')
      return false;

    return isValid((byte) c);
  }

  @Override
  public boolean isValid(byte c) {
    return ('A' <= c && c <= 'I')
      || ('K' <= c && c <= 'N')
      || ('P' <= c && c <= 'Z')
      || '-' == c
      || '*' == c
      || ('a' <= c && c <= 'i')
      || ('k' <= c && c <= 'n')
      || ('p' <= c && c <= 'z');
  }

  @Override
  public boolean isValid(CharSequence line) {
    return line.chars().allMatch(this::isValid);
  }

  @Override
  public boolean isValid(char[] line) {
    for (var c : line)
      if (!isValid(c))
        return false;

    return true;
  }

  @Override
  public boolean isValid(byte[] line) {
    for (var c : line)
      if (!isValid(c))
        return false;

    return true;
  }
}
