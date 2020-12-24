package org.veupathdb.service.multiblast.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Format
{
  private static final String DigestFormat = "SHA-256";

  public static final DecimalFormat Decimals = new DecimalFormat("0.##########");
  public static final ObjectMapper  Json     = new ObjectMapper();

  public static void toSHA256(String value) {
    try {
      var digest = MessageDigest.getInstance(DigestFormat);

      digest.digest(value.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }
}
