package mb.api.model

import jakarta.ws.rs.BadRequestException

private val headerPattern = Regex("^\\s*(\\w+)\\s+(\\d+-\\d+|\\*)/(\\d+|\\*)$")
private val queryPattern = Regex("^(\\d+)(?:-(\\d+))?$")


fun ContentRange(raw: String): ContentRange {
  val (rawUnits, rawRange, _) = headerPattern.matchEntire(raw)?.destructured
    ?: throw BadRequestException("invalid Content-Range format")

  val units = when (rawUnits.lowercase()) {
    "bytes" -> ContentRange.Units.Bytes
    "lines" -> ContentRange.Units.Lines
    else    -> throw BadRequestException("unsupported content range unit type")
  }

  if (rawRange == "*")
    return ContentRange(units, 0..Long.MAX_VALUE)

  val i = rawRange.indexOf('-')

  val start = rawRange.substring(0, i).toULongOrNull() ?: throw BadRequestException("invalid content-range start")
  val end   = rawRange.substring(i+1).toULongOrNull() ?: throw BadRequestException("invalid content-range end")

  if (start > end)
    throw BadRequestException("invalid content range, start must be less than or equal to end")

  return ContentRange(units, start.toLong()..end.toLong())
}

fun ContentRange(units: ContentRange.Units, range: String): ContentRange {
  val (rawStart, rawEnd) = queryPattern.matchEntire(range)?.destructured
    ?: throw BadRequestException("invalid content range param value")

  val start = rawStart.toULongOrNull() ?: throw BadRequestException("invalid content range param start value")

  if (rawEnd.isBlank())
    return ContentRange(units, start.toLong()..Long.MAX_VALUE)

  val end = rawEnd.toULongOrNull() ?: throw BadRequestException("invalid content range param end value")

  return ContentRange(units, start.toLong()..end.toLong())
}

data class ContentRange(val units: Units, val range: LongRange) {
  enum class Units { Bytes, Lines }
}
