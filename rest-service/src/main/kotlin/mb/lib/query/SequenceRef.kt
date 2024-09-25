package mb.lib.query

import java.io.InputStream
import java.io.RandomAccessFile

data class SequenceRef(
  // TODO: sharing RAF instances between sequence refs only works because they
  //       are dealt with linearly.
  private val queryFile: RandomAccessFile,
  private val defLine:   ULongRange?,
  private val sequence:  ULongRange,
) {
  fun getDefLine(): InputStream? = defLine?.let { RAFStream(queryFile, it) }

  fun getSequence(): InputStream = RAFStream(queryFile, sequence)
}


private class RAFStream(
  private val file:  RandomAccessFile,
  private val range: ULongRange,
) : InputStream() {
  private var pos = range.first

  init {
    file.seek(range.first.toLong())
  }

  override fun close() {
    // DO NOTHING, SEQUENCE STREAMS SHOULD NOT BE ABLE TO CLOSE THE PARENT RAF
    // HANDLE.
  }

  override fun read() =
    if (pos <= range.last)
      file.read().also { pos++ }
    else
      -1

  override fun read(b: ByteArray): Int {
    if (pos > range.last)
      return -1

    val bufferLen = b.size.toULong()

    // If the entire buffer read can fit within the legal range, do a simple
    // read.
    if (pos + bufferLen <= range.last)
      return file.read(b).also { pos += it.toULong() }

    pos = range.last

    return file.read(b, 0, b.size - (pos + bufferLen - range.last).toInt())
  }

  override fun read(b: ByteArray, off: Int, len: Int): Int {
    if (pos > range.last)
      return -1

    val readLen = len.toULong()

    if (pos + readLen <= range.last)
      return file.read(b, off, len).also { pos += it.toULong() }

    pos = range.last

    return super.read(b, off, len - (pos + readLen - range.last).toInt())
  }
}
