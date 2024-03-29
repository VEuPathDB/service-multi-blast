package mblast.query.pipe

import mblast.query.SequenceStateRunnable
import mblast.util.io.InStream
import java.io.Closeable
import java.io.File
import java.io.OutputStream

/**
 * # BLAST+ Multi-Sequence Query File Writer
 *
 * Writes out a multi-sequence query stream to one or more files.
 *
 * If the input query contains only one sequence, one file will be written.
 *
 * If the input query contains more than one sequence, one fill will be written
 * for the overall query, and an additional file will be written for each
 * individual sequence in the query.
 *
 * This type performs no validation and assumes the input stream is known to
 * contain a valid BLAST query.
 *
 * On exception, this type will automatically attempt to clean up any files that
 * were created for the failed process.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 *
 * @constructor Constructs a new `SequenceFileWriter` instance.
 *
 * @param fileNamePrefix Prefix for the names of the files generated by the
 * execution of this file writer.
 *
 * File names will follow the pattern `${fileNamePrefix}-${fileIndex}.txt`.
 *
 * `${fileIndex}` will be an incrementing int value starting from `0` for the
 * overall query file, and continuing for individual sequences in a
 * multi-sequence query.
 *
 * For example, given the [fileNamePrefix] value "foobar" a query with a single
 * sequence would generate the file name:
 * ```
 * foobar-0.txt
 * ```
 *
 * Given the same `fileNamePrefix` value of "foobar", a query with 3 sequences
 * would generate the file names:
 * ```
 * foobar-0.txt
 * foobar-1.txt
 * foobar-2.txt
 * foobar-3.txt
 * ```
 *
 * @param targetDir Target directory in which the query files will be written
 * into.
 *
 * The given directory must already exist before this file writer is executed.
 *
 * @param stream Input stream containing the BLAST query that will be written
 * out to file(s).
 */
class SequenceFileWriter(
  private val fileNamePrefix: String,
  private val targetDir: File,
  private val stream: InStream,
) : SequenceStateRunnable(), Closeable {

  /**
   * Handle on the file that will contain the entire multi-query.
   */
  private val overallOutputFile: File
  private val overallOutputStream: OutputStream

  /**
   * List of all the child sequences under the multi-query in the order they
   * appear in the input stream.
   *
   * This list will be empty for queries that contain only a single sequence.
   */
  private val childOutputFiles: MutableList<File>

  /**
   * Handle on the current sub-query being read (if any).
   */
  private var currentChildFile: File?
  private var currentChildStream: OutputStream?


  init {
    if (!targetDir.exists())
      throw IllegalArgumentException("Target directory must already exist.")

    overallOutputFile = makeFile(0)
    overallOutputStream = overallOutputFile.outputStream().buffered()
    childOutputFiles = ArrayList(8)
    currentChildFile = null
    currentChildStream = null
  }

  /**
   * Consumes the given input stream and pipes the sequences out to a collection
   * of files.
   *
   * The returned result will contain an overall query file which contains the
   * entirety of the input stream contents.
   *
   * The returned result will also contain a list of sub-query files for each
   * individual sequence **if and only if** the input query contained more than
   * one sequence.
   *
   * If the input stream contains only one sequence, the returned result
   * sub-query list will be empty as the overall query file is the only result.
   */
  override fun run() {
    try {
      super.run()
    } catch (e: Throwable) {
      cleanUp()
      throw e
    } finally {
      close()
    }
  }

  fun getResult() = SequenceFileWriteResult(overallOutputFile, childOutputFiles)

  override fun handleChar(c: Int) {
    if (c > -1) {
      overallOutputStream.write(c)
      currentChildStream?.write(c)
    }
  }

  override fun nextCharacter() = stream.read()

  override fun onSequenceStart() {
    when (sequenceCount) {
      // If this was called before the first sequence was hit, then something
      // has gone wrong in the underlying state machine.
      0 -> throw IllegalStateException()

      // If this is the first sequence, then there is nothing to do.
      // If there is only one sequence then we only need the overall query file.
      1 -> {}

      // If this is the second sequence, we know that we are going to need
      // sub-query files for the individual sequences.
      2 -> {
        // Since there are multiple sequences in this query, we can't just have
        // the one file represent the whole thing, copy over the first sequence
        // to its own file before continuing to write to the overall query file.
        copyOverallFirstChild()

        // Set up a file for the coming sequence.
        newChild()
      }

      // Else we are on the third sequence or later and we have already
      // established that we need sub-query files.
      // Set up the next one.
      else -> newChild()
    }
  }

  override fun onQueryComplete() {
    overallOutputStream.flush()
    overallOutputStream.close()
    currentChildStream?.flush()
    currentChildStream?.close()
  }

  override fun close() {
    stream.close()
    overallOutputStream.close()
    currentChildStream?.close()
  }

  /**
   * Closes the current child file and stream and opens a new one.
   */
  private fun newChild() {
    currentChildStream?.also {
      it.flush()
      it.close()
    }

    // + 1 because index 0 is reserved for the overall file.
    currentChildFile   = makeFile(childOutputFiles.size + 1).also {
      currentChildStream = it.outputStream().buffered()
      childOutputFiles.add(it)
    }
  }

  /**
   * Copies the contents of the overall output file to a new child file.
   */
  private fun copyOverallFirstChild() {
    // Create a new child file
    newChild()

    // Copy over the contents of our overall file
    overallOutputStream.flush()
    overallOutputFile.inputStream().use { it.transferTo(currentChildStream) }
  }

  /**
   * Closes the streams and attempts to delete all files created by this
   * process.
   */
  private fun cleanUp() {
    close()
    overallOutputFile.delete()
    childOutputFiles.forEach { it.delete() }
  }

  private fun makeFile(index: Int) =
    File(targetDir, "$fileNamePrefix-$index.txt").also { it.createNewFile() }
}