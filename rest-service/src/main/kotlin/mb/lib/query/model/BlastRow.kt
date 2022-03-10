package mb.lib.query.model

import mb.lib.model.JobStatus
import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

open class BlastRow(
  var jobID:     HashID,
  var config:    BlastConfig?    = null,
  var query:     String?         = null,
  var queueID:   Int?            = null,
  var projectID: String?         = null,
  var status:    JobStatus?      = null,
  var createdOn: OffsetDateTime? = null,
  var deleteOn:  OffsetDateTime? = null,
) {

  constructor(old: BlastRow) : this(
    old.jobID,
    old.config,
    old.query,
    old.queueID,
    old.projectID,
    old.status,
    old.createdOn,
    old.deleteOn,
  )

  override fun toString(): String {
    return "BlastRow(jobID=$jobID)"
  }
}
