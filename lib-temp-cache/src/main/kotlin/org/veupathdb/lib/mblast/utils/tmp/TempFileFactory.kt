package org.veupathdb.lib.mblast.utils.tmp

import java.io.File

/**
 * Factory that produces expiring temporary files.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 */
sealed interface TempFileFactory {

  /**
   * Directory in which all temp files will be created.
   */
  val rootDir: File

  /**
   * Default temporary file lifetime as a duration in milliseconds.
   *
   * This value is the duration that will be used when calling [create] without
   * providing an expiration.
   *
   * Examples:
   * ```
   * 10000   - 10 seconds
   * 300000  - 5 minutes
   * 3600000 - 1 hour
   * ```
   */
  val defaultLifetime: Long

  /**
   * Interval in milliseconds at which the temp file cleanup process will be
   * automatically executed.
   */
  val cleanupInterval: Long

  /**
   * Creates a temp file with the configured [defaultLifetime].
   */
  fun create(): File

  /**
   * Creates a temp file with the given [lifeTime].
   */
  fun create(lifeTime: Long): File

  /**
   * Manually triggers a temp file cleanup.
   *
   * The file cleanup will go through all the non-expired created temp files and
   * delete those whose lifetime has passed.
   */
  fun cleanup()

  /**
   * Stops the thread backing this [TempFileFactory], meaning no more automatic
   * temp file cleanup will occur.
   */
  fun shutDown()
}