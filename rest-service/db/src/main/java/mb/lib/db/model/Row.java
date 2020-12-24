package mb.lib.db.model;

class Row
{
  private final byte[] hash;

  Row(byte[] hash) {
    this.hash = new byte[32];
    System.arraycopy(hash, 0, this.hash, 0, 32);
  }

  public byte[] getHash() {
    // Copy so callers can't mutate the internal hash value.
    var out = new byte[32];
    System.arraycopy(this.hash, 0, out, 0, 32);

    return out;
  }
}
