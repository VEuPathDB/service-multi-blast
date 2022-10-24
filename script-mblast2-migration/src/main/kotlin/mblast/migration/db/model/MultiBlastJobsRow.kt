package mblast.migration.db.model

import com.fasterxml.jackson.databind.JsonNode
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

data class MultiBlastJobsRow(
  val jobDigest: HashID,
  val jobConfig: JsonNode,
  val query: String,
  val projectID: String,
  val createdOn: OffsetDateTime,
)