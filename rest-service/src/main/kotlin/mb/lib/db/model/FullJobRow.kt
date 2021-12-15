package mb.lib.db.model

import java.io.File

interface FullJobRow: ShortJobRow, AutoCloseable
{
  /**
   * The blast tool configuration submitted for this job.
   */
  val config: String

  /**
   * The raw query submitted for this job.
   */
  val query: File

  override fun close() {
    query.delete()
  }
}
