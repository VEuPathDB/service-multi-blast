package mb.lib.db.model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class JobHash
{
  public static final byte   HASH_SIZE = 32;
  public static final String HASH_TYPE = "SHA-256";

  private static final String
    ERR_BAD_SIZE = "Input byte array was not "
    + HASH_SIZE
    + " bytes in length.";

  private static final char[] table = {
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
  };

  private final byte[] data;

  private JobHash(byte[] data) {
    this.data = data;
  }

  public byte[] getBytes() {
    return data;
  }

  @Override
  public String toString() {
    var out = new StringBuilder(2 * HASH_SIZE);

    for (var b : data) {
      out.append(table[(b >> 4) & 15]).append(table[b & 15]);
    }

    return out.toString();
  }

  public static JobHash newFromBytes(byte[] data) {
    if (data.length != HASH_SIZE)
      throw new IllegalArgumentException(ERR_BAD_SIZE);

    return new JobHash(data);
  }

  public static JobHash hashValue(String value) throws Exception {
    var dig = MessageDigest.getInstance(HASH_TYPE);
    return newFromBytes(dig.digest(value.getBytes(StandardCharsets.UTF_8)));
  }
}
