package org.veupathdb.service.multiblast.service.valid;

// Legal Characters
//   ABCDEFGHIKLMNPQRSTUVWXYZ*-
//   abcdefghiklmnpqrstuvwxyz*-
public class ProteinSequenceValidator implements SequenceValidator
{
  @Override
  public String kind() {
    return "protein";
  }

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
}
