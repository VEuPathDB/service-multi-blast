package mblast.util.io

import java.io.InputStream

/**
 * InputStream compatibility wrapper.
 *
 * Wraps an [InputStream] and implements the [InStream] interface to allow the
 * wrapped `InputStream` to be used as either type.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 */
class InputIOStream(private val raw: InputStream) : InputStream(), InStream {
  override fun read() = raw.read()

  override fun read(b: ByteArray) = raw.read(b)

  override fun read(b: ByteArray, off: Int, len: Int) = raw.read(b, off, len)

  override fun close() = raw.close()
}