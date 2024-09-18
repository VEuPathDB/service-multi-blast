package mb.api.service.util

import java.io.BufferedReader
import java.io.InputStream
import java.io.OutputStream

internal class LineRangeStream(private val source: BufferedReader, private val range: LongRange) : RangedStream {

  constructor(source: InputStream, range: LongRange) : this(source.bufferedReader(), range)

  override fun write(output: OutputStream) {
    var line = 1L
    val out = output.bufferedWriter()
    source.useLines { it.forEach {
      if (line in range) {
        out.write(it)
        out.newLine() // TODO: this should possibly be CRLF for safety?
      } else if (line > range.last) {
        // no need to iterate to the end of the file if we've exceeded the range
        return@useLines
      }

      line++
    } }
    out.flush()
  }
}
