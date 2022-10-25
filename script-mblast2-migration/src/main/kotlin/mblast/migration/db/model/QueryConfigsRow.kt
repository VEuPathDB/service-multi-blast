package mblast.migration.db.model

import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

data class QueryConfigsRow(
  val queryJobID: HashID,
  val projectID: String,
  val config: BlastQueryBase,
  val query: String,
  val createdOn: OffsetDateTime
)
