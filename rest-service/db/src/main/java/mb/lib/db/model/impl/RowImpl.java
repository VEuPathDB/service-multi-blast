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
}
