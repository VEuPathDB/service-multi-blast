package mb.lib.db.model.impl

import mb.lib.db.model.ShortJobRow
import mb.lib.model.JobStatus
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

open class ShortJobRowImpl(
  jobID: HashID,
  override val queueID: Int,
  override val createdOn: OffsetDateTime,
  override val deleteOn: OffsetDateTime,
  override val status: JobStatus,
  override val projectID: String,
): RowImpl(jobID), ShortJobRow
