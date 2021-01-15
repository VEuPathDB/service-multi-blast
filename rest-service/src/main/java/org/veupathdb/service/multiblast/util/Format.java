package org.veupathdb.service.multiblast.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Format
{
  public static final byte   HASH_SIZE = 32;
  public static final String HASH_TYPE = "SHA-256";

  public static final DecimalFormat Decimals = new DecimalFormat("0.##########");
  public static final ObjectMapper  Json     = new ObjectMapper();

  private static final byte[] hexTable = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

  public static String toHexString(byte[] value) {
    var tmp = new byte[value.length * 2];
    for (int i = 0, j = 0; i < value.length; i++) {
      tmp[j++] = hexTable[value[i] >>> 4];
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

  public static byte[] toSHA256(String value) {
    try {
      var dig = MessageDigest.getInstance(HASH_TYPE);
      return dig.digest(value.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] toSHA256(InputStream value) {
    var buffer = new byte[8192];
    try {
      var dig = MessageDigest.getInstance(HASH_TYPE);
      var buf = new BufferedInputStream(value);
      int n;
      while ((n = buf.read(buffer)) > 0) {
        dig.update(buffer, 0, n);
      }
      return dig.digest();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
