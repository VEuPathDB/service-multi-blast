package mb.lib.db.model;

public interface Row
{
  /**
   * @return the 32 byte job hash value.
   */
  byte[] jobHash();
}
