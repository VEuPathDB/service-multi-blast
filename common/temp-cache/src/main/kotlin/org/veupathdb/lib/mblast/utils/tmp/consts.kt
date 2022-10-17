package org.veupathdb.lib.mblast.utils.tmp

/**
 * Default lifetime for a temp file.
 */
internal const val DEFAULT_FILE_LIFE_TIME = 10L * 60 * 1000 // 10 minutes

/**
 * Default temp directory path.
 */
internal const val DEFAULT_TEMP_DIR_PATH = "/tmp"

/**
 * Default cleanup process interval.
 */
internal const val CLEANUP_INTERVAL = 10L * 60 * 1000 // 10 min