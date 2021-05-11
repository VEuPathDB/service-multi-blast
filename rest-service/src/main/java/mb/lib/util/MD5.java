package mb.lib.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class MD5
{
  private static final String HashType = "MD5";

  public static byte[] hash(String value) {
    try {
      var dig = MessageDigest.getInstance(HashType);
      return dig.digest(value.getBytes(StandardCharsets.UTF_8));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
