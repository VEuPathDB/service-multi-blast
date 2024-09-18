package mb.api.service.util

import java.io.BufferedInputStream
import java.io.InputStream
import java.io.OutputStream

internal class ByteRangeStream(private val source: BufferedInputStream, private val range: LongRange) : RangedStream {
  constructor(source: InputStream, range: LongRange) : this(source.buffered(), range)

  override fun write(output: OutputStream) {
    var red = 0L
    val tgt = range.last+1-range.first
    val out = output.buffered()

    source.use {
      it.skipNBytes(range.first)

      while (red < tgt) {
        out.write(it.read())
        red++
      }
    }

    out.flush()
  }
}
