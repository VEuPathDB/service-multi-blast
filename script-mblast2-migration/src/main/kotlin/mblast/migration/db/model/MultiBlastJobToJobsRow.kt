package mblast.migration.db.model

import org.veupathdb.lib.hash_id.HashID

data class MultiBlastJobToJobsRow(
  val jobDigest: HashID,
  val parentDigest: HashID,
  val position: Int
)
