package mblast.util.io

import java.io.InputStream
import java.io.OutputStream

/**
 * InputStream that writes to a "tee" OutputStream as bytes are read.
 *
 * Each byte that is read from this input stream will be written to the given
 * output stream.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 *
 * @constructor Constructs a new `TeeInputStream` instance.
 *
 * @param input `InputStream` that will be wrapped by the new `TeeInputStream`.
 *
 * @param tee `OutputStream` that will be written to when the new `InputStream`
 * is read from.
 *
 * @param closeTeeOnClose Whether the given `OutputStream` ([tee]) should also
 * be closed when the [close] method is called on the new `TeeInputStream`.
 */
class TeeInputStream(
  private val input: InputStream,
  private val tee: OutputStream,
  private val closeTeeOnClose: Boolean = false
) : InputStream() {

  /**
   * Reads the next byte of data from the wrapped `InputStream`, writes it to
   * the tee `OutputStream`, then returns it.
   *
   * The value byte is returned as an `Int` in the range `0` to `255`.  If no
   * byte is available because the end of the stream has been reached, the value
   * `-1` is returned.
   *
   * This method blocks until input data is available, the end of the stream is
   * detected, or an exception is thrown.
   *
   * @return the next byte of data, or `-1` if the end of the stream is reached.
   */
  override fun read(): Int {
    val byte = input.read()
    tee.write(byte)
    return byte
  }

  /**
   * Closes this `InputStream` and optionally the tee `OutputStream`, which
   * releases any system resources associated with the streams.
   */
  override fun close() {
    input.close()
    if (closeTeeOnClose)
      tee.close()
  }
}
