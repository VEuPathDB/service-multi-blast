package mb.lib.query.model

import mb.lib.model.JobStatus
import org.veupathdb.lib.hash_id.HashID
import java.time.OffsetDateTime

open class UserBlastRow(
  jobID:               HashID,
  config:              JobConfig?      = null,
  query:               String?         = null,
  queueID:             Int?            = null,
  projectID:           String?         = null,
  status:              JobStatus?      = null,
  createdOn:           OffsetDateTime? = null,
  deleteOn:            OffsetDateTime? = null,
  var userID:          Long,
  var description:     String?         = null,
  var maxDownloadSize: Long            = 0,
  var runDirectly:     Boolean         = false,
): BlastRow(jobID, config, query, queueID, projectID, status, createdOn, deleteOn) {

  constructor(
    old: BlastRow,
    userID: Long,
    description: String? = null,
    maxDownloadSize: Long = 0,
    runDirectly: Boolean = false,
  ) : this(
    jobID           = old.jobID,
    config          = old.config,
    query           = old.query,
    queueID         = old.queueID,
    projectID       = old.projectID,
    status          = old.status,
    createdOn       = old.createdOn,
    deleteOn        = old.deleteOn,
    userID          = userID,
    description     = description,
    maxDownloadSize = maxDownloadSize,
    runDirectly     = runDirectly
  )

  constructor(old: UserBlastRow): this(
    jobID           = old.jobID,
    config          = old.config,
    query           = old.query,
    queueID         = old.queueID,
    projectID       = old.projectID,
    status          = old.status,
    createdOn       = old.createdOn,
    deleteOn        = old.deleteOn,
    userID          = old.userID,
    description     = old.description,
    maxDownloadSize = old.maxDownloadSize,
    runDirectly     = old.runDirectly,
  )

  override fun toString(): String {
    return "UserBlastRow(jobID=${jobID}, userID=${userID})"
  }
}
