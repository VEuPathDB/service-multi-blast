package org.veupathdb.service.mblast.query.model

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import java.io.File
import java.io.Reader

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
   * Handle on the config file in the filesystem.
   */
  val configFile: File

  /**
   * Handle on the query file in the filesystem.
   */
  val queryFile: File

  /**
   * Returns a stream over the raw FASTA query.
   */
  fun getQueryReader(): Reader

  /**
   * Returns a stream over the raw JSON BLAST+ tool configuration.
   */
  fun getConfigReader(): Reader

  /**
   * Loads the BLAST+ tool configuration into memory and returns it.
   */
  fun getConfig(): BlastQueryBase

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
  override val configFile: File,
  override val queryFile: File
) : BasicQueryConfig {
  override fun getQueryReader() = queryFile.reader()

  override fun getConfigReader() = configFile.reader()

  override fun getConfig() = configFile.inputStream().use { Blast.of(Json.parse(it)) as BlastQueryBase }

  override fun getQuery() = configFile.readText()

  override fun close() {
    configFile.delete()
    queryFile.delete()
  }
}