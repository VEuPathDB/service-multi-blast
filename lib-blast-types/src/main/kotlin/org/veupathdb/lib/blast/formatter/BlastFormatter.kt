package org.veupathdb.lib.blast.formatter

import org.veupathdb.lib.blast.common.BlastCLI
import org.veupathdb.lib.blast.formatter.fields.Archive
import org.veupathdb.lib.blast.formatter.fields.RID

/**
 * Stand-alone BLAST formatter client, version 2.13.0+
 */
interface BlastFormatter : BlastCLI {
  /**
   * -rid `<String>`
   */
  var rid: RID
  fun rid(value: String)

  /**
   * -archive `<File_In>`
   */
  var archive: Archive
  fun archive(value: String)

  /**
   * Clones the current config and all it's field values.
   *
   * @return A cloned copy of this config instance.
   *
   * @since v8.2.0
   */
  override fun clone(): BlastFormatter
}