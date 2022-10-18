package mblast.util

fun ByteArray.toHexString() =
  StringBuilder(size * 2).also { sb -> forEach { b -> sb.appendHex(b.toUByte()) } }
    .toString()

inline infix fun UByte.shr(p: Int) = (toInt() shr p).toUByte()

private fun StringBuilder.appendHex(b: UByte) =
  if (b < 15u) {
    append('0')
    append(byteToHexChar(b))
  } else {
    append(byteToHexChar(b shr 4))
    append(byteToHexChar(b and 15u))
  }

private inline fun byteToHexChar(b: UByte) =
  if (b < 10u) { b + 48u } else { b + 55u }.toInt().toChar()

