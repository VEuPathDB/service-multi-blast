package mb.lib.db.model

import mb.lib.model.JobStatus
import java.time.OffsetDateTime

interface ShortJobRow: Row
{
  val queueID: Int

  val createdOn: OffsetDateTime

  val deleteOn: OffsetDateTime

  val status: JobStatus

  val projectID: String
}
