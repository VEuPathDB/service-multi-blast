package mb.lib.util;

import java.nio.charset.StandardCharsets;

public class Hash
{
  private static final int HashSize = 16;

  public static String hashToString(byte[] val) {
    if (val.length != HashSize)
      throw new IllegalArgumentException("Invalid hash value length.");

    var out = new byte[val.length * 2];

    for (var i = 0; i < val.length; i++) {
      byteToHex(out, i*2, val[i]);
    }

    return new String(out);
  }

  public static byte[] stringToHash(String val) {
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
