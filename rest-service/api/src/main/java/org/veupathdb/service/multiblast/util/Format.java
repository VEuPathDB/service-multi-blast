package org.veupathdb.service.multiblast.util;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Format
{
  private static final Logger log = LogManager.getLogger(Format.class);

  public static final byte   HASH_SIZE = 20;
  public static final String HASH_TYPE = "MD5";

  public static final DecimalFormat Decimals = new DecimalFormat("0.##########");
  public static final ObjectMapper  Json     = new ObjectMapper();

  private static final byte[] hexTable = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

  public static String toHexString(byte[] value) {
    log.trace("Format#toHexString(byte[])");

    var tmp = new byte[value.length * 2];
    for (int i = 0, j = 0; i < value.length; i++) {
      tmp[j++] = hexTable[(value[i] >>> 4) & 0xF];
      tmp[j++] = hexTable[value[i] & 0xF];
    }
    return new String(tmp, StandardCharsets.UTF_8);
  }

  public static byte[] hexToBytes(String value) {
    log.trace("Format#hexToBytes(String)");

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
    log.trace("Format#isHex(String)");

    for (var b : val.getBytes(StandardCharsets.UTF_8)) {
      if (b < '0' || b > 'f' || b > '9' && b < 'A' || b > 'F' && b < 'a') {
        return false;
      }
    }

    return true;
  }

  public static byte[] toSHA256(String value) {
    log.trace("Format#toSHA256(String)");

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
