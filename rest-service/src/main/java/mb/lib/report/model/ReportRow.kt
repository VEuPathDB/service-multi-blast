package mb.lib.report.model

import mb.lib.model.HashID
import mb.lib.model.JobStatus
import org.veupathdb.lib.blast.BlastFormatter
import java.time.OffsetDateTime

open class ReportRow(
  val reportID: HashID,
  val jobID: HashID,
  var status: JobStatus,
  val config: BlastFormatter,
  var queueID: Int,
  val createdOn: OffsetDateTime
)