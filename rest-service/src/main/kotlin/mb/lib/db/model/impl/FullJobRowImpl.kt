package mb.lib.db.model.impl

import mb.lib.db.model.FullJobRow
import mb.lib.model.HashID
import mb.lib.model.JobStatus
import java.io.File
import java.time.OffsetDateTime

class FullJobRowImpl(
  jobID: HashID,
  queueID: Int,
  createdOn: OffsetDateTime,
  deleteOn: OffsetDateTime,
  status: JobStatus,
  projectID: String,
  override val config: String,
  override val query: File,
): ShortJobRowImpl(jobID, queueID, createdOn, deleteOn, status, projectID), FullJobRow
