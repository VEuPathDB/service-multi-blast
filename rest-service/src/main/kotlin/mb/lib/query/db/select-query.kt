package mb.lib.query.db

import mb.lib.util.logger
import org.veupathdb.lib.hash_id.HashID
import java.io.InputStream
import java.io.OutputStream
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

// language=oracle
private const val Query = """
  SELECT
    query
  FROM
    multiblast.multiblast_jobs
  WHERE
    job_digest = ?
"""

internal fun Connection.selectQuery(jobID: HashID): MBlastQuery? {
  val ps = prepareStatement(Query)

  try {
    ps.setBytes(1, jobID.bytes)

    val rs = ps.executeQuery()

    try {
      if (!rs.next()) {
        try { ps.close() } catch (e: Throwable) {}
        try { rs.close() } catch (e: Throwable) {}
        return null
      }

      return MBlastQuery(jobID, rs.getAsciiStream(1), rs, ps)
    } catch (e: Throwable) {
      try { rs.close() } catch (e: Throwable) {}
      throw e
    }

  } catch (e: Throwable) {
    try { ps.close() } catch (e: Throwable) {}
    throw e
  }
}

internal class MBlastQuery(
  private val jobID:      HashID,
  private val stream:     InputStream,
  private val results:    ResultSet,
  private val statement:  PreparedStatement,
) : InputStream(), AutoCloseable {
  override fun read() = stream.read()

  override fun read(b: ByteArray) = stream.read(b)

  override fun read(b: ByteArray, off: Int, len: Int) = stream.read(b, off, len)

  override fun readAllBytes() = stream.readAllBytes()!!

  override fun readNBytes(len: Int) = stream.readNBytes(len)!!

  override fun readNBytes(b: ByteArray?, off: Int, len: Int) = stream.readNBytes(b, off, len)

  override fun skip(n: Long) = stream.skip(n)

  override fun skipNBytes(n: Long) = stream.skipNBytes(n)

  override fun available() = stream.available()

  override fun mark(readlimit: Int) = stream.mark(readlimit)

  override fun reset() = stream.reset()

  override fun markSupported() = stream.markSupported()

  override fun transferTo(out: OutputStream) = stream.transferTo(out)

  override fun close() {
    forceClose(stream::close)
    forceClose(results::close)
    forceClose(statement::close)
  }

  private fun forceClose(fn: () -> Unit) {
    try {
      fn()
    } catch (e: Throwable) {
      logger().error("failed to close resource for job $jobID", e)
    }
  }
}

