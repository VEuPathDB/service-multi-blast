package mb.lib.db.model.impl;

import mb.lib.db.model.Row;

public class RowImpl implements Row
{
  private final byte[] hash;

  public RowImpl(byte[] hash) {
    this.hash = hash;
  }

  @Override
  public byte[] jobHash() {
    return hash;
  }

  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  public String printID() {
    char[] hexChars = new char[hash.length * 2];
    for (int j = 0; j < hash.length; j++) {
      int v = hash[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }

  @Override
  public String toString() {
    return "Row{hash=" + printID() + '}';
  }
}
