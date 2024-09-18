package mb.api.service.util

import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream

internal class UnrangedStream(private val source: BufferedInputStream) : RangedStream {
  constructor(source: InputStream) : this(source.buffered())

  override fun write(output: OutputStream) {
    val out = output.buffered()
    source.use { it.transferTo(out) }
    out.flush()
  }
}
