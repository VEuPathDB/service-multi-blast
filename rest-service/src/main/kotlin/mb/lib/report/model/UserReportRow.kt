package mb.lib.report.model

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import mb.lib.model.JobStatus
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

@JsonInclude(NON_DEFAULT)
class UserReportRow(
  reportID: HashID,
  jobID: HashID,
  status: JobStatus,
  config: BlastFormatter,
  queueID: Int,
  createdOn: OffsetDateTime,
  val userID: Long,
  val description: String?
) : ReportRow(reportID, jobID, status, config, queueID, createdOn) {
  constructor(row: ReportRow, userID: Long, description: String?) : this(
    row.reportID,
    row.jobID,
    row.status,
    row.config,
    row.queueID,
    row.createdOn,
    userID,
    description
  )
}