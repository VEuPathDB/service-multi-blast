package mb.lib.db.model.impl;

import java.util.Arrays;

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

  protected String printID() {
    var strb = new char[hash.length * 2];

    for (var i = 0; i < hash.length; i++) {
      var j = i*2;
      var a = (hash[i] >> 4) & 0xF;
      var b = hash[i] & 0xF;
      strb[j] = (char) (a > 10 ? a + 'A' : a + '0');
      strb[j+1] = (char) (b > 10 ? b + 'A' : b + '0');
    }

    return new String(strb);
  }

  @Override
  public String toString() {
    return "Row{hash=" + printID() + '}';
  }
}
