package mb.lib.util

private const val HashSize = 16

fun hashToString(value: ByteArray): String {
  if (value.size != HashSize)
    throw IllegalArgumentException("Invalid hash value length.")

  val out = ByteArray(value.size * 2)

  for (i in value.indices) {
    byteToHex(out, i*2, value[i])
  }

  return String(out)
}

fun stringToHash(value: String): ByteArray {
  val len = value.length

  if (len != HashSize * 2)
    throw IllegalArgumentException("Invalid hash value length.")

  val out = ByteArray(len/2)
  val inp = value.toCharArray()

  var i = 0
  while (i < len) {
    out[i/2] = charsToByte(inp[i++], inp[i++])
  }

  return out
}

fun byteToHex(buf: ByteArray, pos: Int, b: Byte) {
  buf[pos]   = byteToChar(((b.toInt() shr 4) and 0x0F).toByte())
  buf[pos+1] = byteToChar((b.toInt() and 0x0F).toByte())
}

fun byteToChar(b: Byte): Byte {
  return if (b > 15)
    throw IllegalArgumentException("Cannot translate values > 15 to a hex digit.")
  else
    (if (b > 9) 'A' + (b - 10) else '0' + b.toInt()).code.toByte()
}

fun charsToByte(a: Char, b: Char) = (charToByte(a) shl 4 or charToByte(b)).toByte()

fun charToByte(v: Char) = when(v.code) {
  in 97..102 -> v - 97 + 10
  in 65..70  -> v - 65 + 10
  in 48..57  -> v - 48
  else         -> throw IllegalArgumentException("Invalid hex character $v")
}.code
