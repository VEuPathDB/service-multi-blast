package mb.lib.util

internal inline val Char.isWhitespace
  get() = this == ' ' || this == '\t'


internal fun Iterable<*>.joinToFriendlyString(): String {
  val it = iterator()

  if (!it.hasNext()) {
    return ""
  }

  val sb = StringBuilder(1024)
  sb.append(it.next())

  while (it.hasNext()) {
    val next = it.next()
    sb.append(if (it.hasNext()) ", " else ", and ").append(next)
  }

  return sb.toString()
}
