package mblast.migration.db.model

import org.veupathdb.lib.hash_id.HashID

data class MultiBlastUsersRow(
  val jobDigest: HashID,
  val userID: Long,
  val description: String?,
  val maxDownloadSize: Long,
  val runDirectly: Boolean,
)
