package org.veupathdb.service.multiblast.service.valid;

public interface SequenceValidator
{
  boolean isValid(int c);
  boolean isValid(char c);
  boolean isValid(byte c);
  boolean isValid(CharSequence line);
  boolean isValid(char[] line);
  boolean isValid(byte[] line);
}
