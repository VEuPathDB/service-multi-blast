package mblast.util.io

import io.foxcapades.lib.kps.kpd.UByteDeque
import java.io.OutputStream

private const val ASCII_NUL: UByte = 0u
private const val ASCII_LF: UByte = 10u
private const val ASCII_CR: UByte = 13u

/**
 * OutputStream implementation that outputs lines of text to a logger function.
 *
 * **Example**
 * ```
 * // Some text to log
 * val myText = """
 * Hello
 * Goodbye
 * """
 *
 * // Create a log stream wrapping a logger method
 * val logStream = LoggingOutputStream(logger::debug)
 *
 * // Pipe input stream to the logging output stream
 * myText.byteInputStream().transferTo(logStream)
 * ```
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 *
 * @constructor Constructs a new `LoggingOutputStream` instance.
 *
 * @param logWriter Function that will be called with collected log lines.
 */
@Suppress("NOTHING_TO_INLINE")
class LoggingOutputStream(private val logWriter: (line: String) -> Unit) : OutputStream() {
  private val buffer = UByteDeque(256)

  override fun write(b: Int) {
    write(b.toUByte())
  }

  override fun flush() {
    flushToLogger()
  }

  override fun close() {
    flushToLogger()
  }

  private inline fun write(b: UByte) {
    if (isLineBreak(b)) {
      flushToLogger()
    } else {
      buffer += b
    }
  }

  @OptIn(ExperimentalUnsignedTypes::class)
  private inline fun flushToLogger() {
    logWriter(buffer.toArray().asByteArray().decodeToString())
    buffer.clear()
  }

  private inline fun isLineBreak(b: UByte)=
    b == ASCII_LF || b == ASCII_CR || b == ASCII_NUL
}