package mb.lib.db.model.impl;

import mb.lib.db.model.Row;
import mb.lib.model.HashID;

public class RowImpl implements Row
{
  private final HashID hash;

  public RowImpl(HashID hash) {
    this.hash = hash;
  }

  @Override
  public HashID jobID() {
    return hash;
  }

  @Override
  public String toString() {
    return "Row{hash=" + hash + '}';
  }
}
