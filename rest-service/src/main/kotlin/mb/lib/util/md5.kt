package mb.lib.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

private const val HashType = "MD5";

fun md5(value: String): ByteArray =
  MessageDigest.getInstance(HashType).digest(value.toByteArray(StandardCharsets.UTF_8))
