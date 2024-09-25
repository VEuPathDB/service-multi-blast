@file:Suppress("NOTHING_TO_INLINE")
package mb.lib.dmnd

import jakarta.ws.rs.InternalServerErrorException
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.UUID

internal const val NUL = '\u0000'
internal const val EOF = '\u0004'

internal inline fun isWhitespace(c: Char) = c == ' ' || c == '\t'
internal inline fun isNewLine(c: Char) = c == '\n' || c == '\r'
internal inline fun isDigit(c: Char) = c in '0' .. '9'
internal inline fun isIgnorable(c: Char) = isDigit(c) || isWhitespace(c)

internal fun InputStream.toIOStream(): InStream =
  if (this is InStream) this else InputIOStream(this)

internal fun OutputStream.toIOStream(): OutStream =
  if (this is OutStream) this else OutputIOStream(this)

internal inline fun <T> withTempFile(content: InStream, fn: (File) -> T): T {
  val tempFile = File("/tmp/${UUID.randomUUID()}")
  tempFile.createNewFile() || throw InternalServerErrorException("failed to create temp file for query upload")
  try {
    tempFile.outputStream().buffered().use { content.transferTo(it.toIOStream()) }
    return fn(tempFile)
  } finally {
    tempFile.delete()
  }
}
