package mb.api.service.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Format
{
  public static final String HASH_TYPE = "MD5";

  public static final ObjectMapper      Json       = new ObjectMapper();
  public static final DateTimeFormatter DateFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

  private static final byte[] hexTable = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

  public static String toString(OffsetDateTime date) {
    return DateFormat.format(date);
  }

  public static String toHexString(byte[] value) {
    var tmp = new byte[value.length * 2];
    for (int i = 0, j = 0; i < value.length; i++) {
      tmp[j++] = hexTable[(value[i] >>> 4) & 0xF];
      tmp[j++] = hexTable[value[i] & 0xF];
    }
    return new String(tmp, StandardCharsets.UTF_8);
  }

  public static byte[] hexToBytes(String value) {
    var raw = value.getBytes(StandardCharsets.US_ASCII);
    var out = new byte[raw.length / 2];

    for (int i = 0, j = 0; i < out.length; i++) {
      out[i] = (byte) (hexToByte(raw[j++]) << 4 | hexToByte(raw[j++]));
    }

    return out;
  }

  public static byte hexToByte(byte v) {
    if (v < 'A')
      return (byte) (v - '0');

    if (v < 'a')
      return (byte) (v - 'A' + 10);

    return (byte) (v - 'a' + 10);
  }

  public static boolean isHex(String val) {
    for (var b : val.getBytes(StandardCharsets.UTF_8)) {
      if (b < '0' || b > 'f' || b > '9' && b < 'A' || b > 'F' && b < 'a') {
        return false;
      }
    }

    return true;
  }

  public static byte[] toHash(String value) {
    try {
      var dig = MessageDigest.getInstance(HASH_TYPE);
      return dig.digest(value.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
