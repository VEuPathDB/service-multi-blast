package org.veupathdb.lib.mblast.utils.tmp

import java.io.File

/**
 * Instantiable Custom Temp File Factory
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  1.0.0
 *
 * @constructor Constructs a new [CustomTempFileFactory] instance.
 *
 * @param rootDir Root directory in which all temp files created by this factory
 * will be created.
 *
 * Defaults to `/tmp`
 *
 * @param defaultLifetime Default lifetime assigned to temp files created
 * without specifying a lifetime in milliseconds.
 *
 * Defaults to 10 min.
 *
 * @param cleanupInterval Temp file cleanup process execution interval in
 * milliseconds.
 *
 * Defaults to 10 min.
 */
class CustomTempFileFactory
@JvmOverloads
constructor(
  rootDir:         File = File(DEFAULT_TEMP_DIR_PATH),
  defaultLifetime: Long = DEFAULT_FILE_LIFE_TIME,
  cleanupInterval: Long = CLEANUP_INTERVAL,
) : TempFileFactoryBase(
  rootDir,
  defaultLifetime,
  cleanupInterval
), TempFileFactory