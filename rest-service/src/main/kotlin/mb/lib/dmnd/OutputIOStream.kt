package mb.lib.dmnd

import java.io.OutputStream

class OutputIOStream(private val raw: OutputStream) : OutputStream(), OutStream {
  override fun write(b: Int) = raw.write(b)

  override fun write(b: ByteArray) = raw.write(b)

  override fun write(b: ByteArray, off: Int, len: Int) = raw.write(b, off, len)

  override fun flush() = raw.flush()

  override fun close() {
    super.close()
    raw.close()
  }
}
