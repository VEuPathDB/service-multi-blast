package mblast.util.io

import java.io.Closeable

private const val BUFFER_SIZE = 8192

/**
 * Simple input stream.
 *
 * An IO interface defining an input stream that is compatible with the JDK's
 * standard library [InputStream] without being an abstract class.  Intended to
 * be used with wrappers to allow implementing an `InputStream`-like type
 * without requiring the class inheritance.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 */
interface InStream : Closeable {
  fun read(): Int

  fun read(b: ByteArray): Int = read(b, 0, b.size)

  fun read(b: ByteArray, off: Int, len: Int): Int {
    if (len == 0)
      return 0

    var c = read()
    if (c == -1)
      return -1

    b[off] = c.toByte()

    var i = 1
    while (i < len) {
      c = read()

      if (c == -1)
        break

      b[off + i] = c.toByte()

      i++
    }

    return i
  }

  fun transferTo(out: OutStream): Long {
    var transferred = 0L

    val buffer = ByteArray(BUFFER_SIZE)
    var red    = read(buffer, 0, BUFFER_SIZE)

    while (red >= 0) {
      out.write(buffer, 0, red)
      transferred += red

      red = read(buffer, 0, BUFFER_SIZE)
    }

    return transferred
  }
}