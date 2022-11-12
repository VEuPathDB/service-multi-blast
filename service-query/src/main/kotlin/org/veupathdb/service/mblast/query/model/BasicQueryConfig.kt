package org.veupathdb.service.mblast.query.model

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import java.io.File
import java.io.Reader
import java.time.OffsetDateTime

/**
 * # Basic Query Configuration
 *
 * Details describing a query job.
 *
 * @author Elizabeth Paige Harper - https://github.com/Foxcapades
 * @since  2.0.0
 */
interface BasicQueryConfig : AutoCloseable {

  /**
   * The Job ID of the query job this config represents.
   */
  val queryJobID: HashID

  /**
   * The target project/site.
   */
  val projectID:  String

  /**
   * BLAST+ tool config.
   */
  val config: BlastQueryBase

  /**
   * Handle on the query file in the filesystem.
   */
  val queryFile: File

  /**
   * Timestamp of when the job was created.
   */
  val createdOn: OffsetDateTime

  /**
   * Returns a stream over the raw FASTA query.
   */
  fun getQueryReader(): Reader

  /**
   * Loads the raw FASTA query into memory and returns it.
   */
  fun getQuery(): String

  /**
   * Releases/clears the resources backing this [BasicQueryConfig] instance.
   */
  override fun close()
}

data class BasicQueryConfigImpl(
  override val queryJobID: HashID,
  override val projectID: String,
  override val config: BlastQueryBase,
  override val queryFile: File,
  override val createdOn: OffsetDateTime,
) : BasicQueryConfig {
  override fun getQueryReader() = queryFile.reader()

  override fun getQuery() = queryFile.readText()

  override fun close() {
    queryFile.delete()
  }
}