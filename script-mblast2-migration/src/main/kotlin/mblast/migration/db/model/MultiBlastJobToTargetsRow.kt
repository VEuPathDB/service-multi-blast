package mblast.migration.db.model

import org.veupathdb.lib.hash_id.HashID

data class MultiBlastJobToTargetsRow(
  val jobDigest: HashID,
  val organism: String,
  val targetFile: String,
)