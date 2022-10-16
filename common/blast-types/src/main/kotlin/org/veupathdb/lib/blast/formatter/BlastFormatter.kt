package org.veupathdb.lib.blast.formatter

import org.veupathdb.lib.blast.common.BlastCLI
import org.veupathdb.lib.blast.formatter.fields.Archive
import org.veupathdb.lib.blast.formatter.fields.RID

/**
 * Stand-alone BLAST formatter client, version 2.11.0+
 */
interface BlastFormatter : BlastCLI {
  /**
   * -rid `<String>`
   */
  var rid: RID

  /**
   * -archive `<File_In>`
   */
  var archive: Archive
}