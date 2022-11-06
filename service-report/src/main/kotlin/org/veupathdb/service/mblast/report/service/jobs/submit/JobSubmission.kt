package org.veupathdb.service.mblast.report.service.jobs.submit

import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.hash_id.HashID

data class JobSubmission(
  val reportID: HashID,
  val queryID: HashID,
  val config: BlastFormatter,
  val userID: Long,
  val userAuth: TwoTuple<String, String>,
  val userSummary: String?,
  val userDescription: String?,
  val addToUserCollection: Boolean,
)
