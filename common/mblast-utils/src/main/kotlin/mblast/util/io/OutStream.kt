package mblast.util.io

import java.io.Closeable

/**
 * Simple output stream.
 *
 * An IO interface defining an output stream that is compatible with the JDK's
 * standard library [OutputStream] without being an abstract class.  Intended to
 * be used with wrappers to allow implementing an `OutputStream`-like type
 * without requiring the class inheritance.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 */
interface OutStream : Closeable {
  fun write(b: Int)

  fun write(b: ByteArray) = write(b, 0, b.size)

  fun write(b: ByteArray, off: Int, len: Int) {
    for (i in 0 until len)
      write(b[off + i].toInt())
  }

  fun flush() {}
}