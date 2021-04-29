package mb.lib.model;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.function.Supplier;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public class HashID
{
  private static final int HashSize = 16;

  private final String strVal;
  private final byte[] byteVal;

  @JsonCreator
  public HashID(String val) {
    strVal = val;
    byteVal = stringToHash(val);
  }

  public HashID(byte[] val) {
    strVal = hashToString(val);
    byteVal = val;
  }

  @JsonValue
  public String string() {
    return strVal;
  }

  public byte[] bytes() {
    return byteVal;
  }

  @Override
  public String toString() {
    return strVal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HashID jobID = (HashID) o;
    return Arrays.equals(byteVal, jobID.byteVal);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(byteVal);
  }

  public static <T extends Throwable> HashID parseOrThrow(
    String val,
    Supplier<T> fn
  ) throws T {
    try {
      return new HashID(val);
    } catch (IllegalArgumentException e) {
      throw fn.get();
    }
  }

  static String hashToString(byte[] val) {
    if (val.length != HashSize)
      throw new IllegalArgumentException("Invalid hash value length.");

    var out = new byte[val.length * 2];

    for (var i = 0; i < val.length; i++) {
      byteToHex(out, i*2, val[i]);
    }

    return new String(out);
  }

  static byte[] stringToHash(String val) {
    var len = val.length();

    if (len != HashSize * 2)
      throw new IllegalArgumentException("Invalid hash value length.");

    var out = new byte[len / 2];
    var in  = val.getBytes(StandardCharsets.UTF_8);

    for (var i = 0; i < len; ) {
      out[i/2] = charsToByte(in[i++], in[i++]);
    }

    return out;
  }

  static void byteToHex(byte[] buf, int pos, byte b) {
    buf[pos]   = byteToChar((byte) ((b >> 4) & 0x0F));
    buf[pos+1] = byteToChar((byte) (b & 0x0F));
  }

  static byte byteToChar(byte b) {
    if (b > 15)
      throw new IllegalArgumentException("Cannot translate values > 15 to a hex digit.");

    return (byte) (b > 9 ? 'A' + (b - 10) : '0' + b);
  }

  static byte charsToByte(byte a, byte b) {
    return (byte) (charToByte(a) << 4 | charToByte(b));
  }

  static byte charToByte(byte v) {
    if (v >= 'a' && v <= 'f')
      return (byte) (v - 'a' + 10);
    if (v >= 'A' && v <= 'F')
      return (byte) (v - 'A' + 10);
    if (v >= '0' && v <= '9')
      return (byte) (v - '0');

    throw new IllegalArgumentException("Invalid hex character " + (char) v);
  }
}
