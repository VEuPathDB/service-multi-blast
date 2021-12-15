package mb.api.service.util

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

//
// Char Codes:
//
// 0: 48
// 9: 57
// A: 65
// F: 70
// a: 97
// f: 102

object Format
{
  private const val HASH_TYPE = "MD5"

  val DateFormat = DateTimeFormatter.ISO_OFFSET_DATE_TIME!!

  private val hexTable = byteArrayOf(
    48, 49, 50, 51, 52, 53, 54, 55, 56, 57,
    65, 66, 67, 68, 69, 70
  )

  fun toString(date: OffsetDateTime) = DateFormat.format(date)!!

  fun toHexString(value: ByteArray): String {
    val tmp = ByteArray(value.size * 2)

    var j = 0
    for (i in value.indices) {
      tmp[j++] = hexTable[(value[i].toInt() shr 4) and 0xF]
      tmp[j++] = hexTable[value[i].toInt() and 0xF]
    }

    return String(tmp, StandardCharsets.UTF_8)
  }

  fun hexToBytes(value: String): ByteArray {
    val raw = value.toByteArray(StandardCharsets.US_ASCII)
    val out = ByteArray(raw.size / 2)

    var j = 0
    for (i in out.indices) {
      out[i] = (hexToByte(raw[j++]) shl 4 or hexToByte(raw[j++])).toByte()
    }

    return out
  }

  fun hexToByte(v: Byte) = when {
    v < 65 -> (v - 48)
    v < 97 -> (v - 65 + 10)
    else   -> (v - 97 + 10)
  }

  fun isHex(value: String): Boolean {
    for (b in value.toByteArray(StandardCharsets.UTF_8)) {
      // if (b < '0' || b > 'F' || 'f' < b < 'A' || 'F' < b < 'a')
      if (b < 48 || b > 102 || b in 58..64 || b in 71..96) {
        return false
      }
    }

    return true
  }

  fun toHash(value: String) = MessageDigest.getInstance(HASH_TYPE).digest(value.toByteArray(StandardCharsets.UTF_8))!!
}
