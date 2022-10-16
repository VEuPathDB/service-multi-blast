package mblast.util.io

import java.io.InputStream

/**
 * InStream compatibility wrapper.
 *
 * Wraps an [InStream] and extends the [InputStream] abstract class to allow the
 * wrapped `InStream` to be used as either type.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 */
class IOInputStream(private val raw: InStream) : InputStream(), InStream {

  override fun read() = raw.read()

  override fun read(b: ByteArray) = raw.read(b)

  override fun read(b: ByteArray, off: Int, len: Int) = raw.read(b, off, len)

  override fun close() = raw.close()
}