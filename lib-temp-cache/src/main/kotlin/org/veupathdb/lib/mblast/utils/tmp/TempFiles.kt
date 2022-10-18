package org.veupathdb.lib.mblast.utils.tmp

import java.io.File

/**
 * Default Expiring Temporary Files Factory
 *
 * Singleton [TempFileFactory] instance.
 *
 * This TempFileFactory instance
 *
 * @see CustomTempFileFactory
 */
object TempFiles
  : TempFileFactoryBase(File("/tmp"), DEFAULT_FILE_LIFE_TIME, CLEANUP_INTERVAL)
  , TempFileFactory
{
  override fun shutDown() {
    throw UnsupportedOperationException("Cannot shut down the TempFiles singleton")
  }
}