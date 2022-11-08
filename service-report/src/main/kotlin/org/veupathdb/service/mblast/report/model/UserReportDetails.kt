package org.veupathdb.service.mblast.report.model

import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

data class UserReportDetails(
  val reportJobID: HashID,
  val queryJobID: HashID,
  val userID: Long,
  val config: BlastFormatter,
  val createdOn: OffsetDateTime,
  val userSummary: String?,
  val userDescription: String?,
)
