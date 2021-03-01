package org.veupathdb.service.multiblast.service.valid;

import mb.lib.config.Config;

// Legal Characters
//   ABCDEFGHIKLMNPQRSTUVWXYZ*-
//   abcdefghiklmnpqrstuvwxyz*-
public class ProteinSequenceValidator implements SequenceValidator
{
  private static final Config conf = Config.getInstance();

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

  @Override
  public boolean isValidLength(int len) {
    return len <= conf.getMaxAASeqSize();
  }

  @Override
  public int maxSeqLength() {
    return conf.getMaxAASeqSize();
  }
}
