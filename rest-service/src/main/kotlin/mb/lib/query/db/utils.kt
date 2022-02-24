@file:JvmName("Util")

package mb.lib.query.db

import mb.lib.model.HashID
import mb.lib.model.JobStatus
import mb.lib.query.model.BlastJobLink
import mb.lib.query.model.BlastRow
import mb.lib.query.model.BlastTargetLink
import mb.lib.query.model.UserBlastRow
import mb.lib.util.convertJobConfig
import java.io.File
import java.io.FileOutputStream
import java.sql.Clob
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.util.*

fun parseBlastRow(rs: ResultSet) = BlastRow(
  jobID     = HashID(rs.getBytes(Column.MultiBlastJobs.JobID)),
  config    = convertJobConfig(rs.getString(Column.MultiBlastJobs.JobConfig)),
  query     = rs.getString(Column.MultiBlastJobs.Query),
  queueID   = rs.getInt(Column.MultiBlastJobs.QueueID),
  projectID = rs.getString(Column.MultiBlastJobs.ProjectID),
  status    = JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status)),
  createdOn = rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime::class.java),
  deleteOn  = rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime::class.java),
)

fun parseUserBlastRow(rs: ResultSet) = UserBlastRow(
  old             = parseBlastRow(rs),
  userID          = rs.getLong(Column.MultiBlastUsers.UserId),
  description     = rs.getString(Column.MultiBlastUsers.Description),
  maxDownloadSize = rs.getLong(Column.MultiBlastUsers.MaxDownloadSize),
  runDirectly     = rs.getBoolean(Column.MultiBlastUsers.RunDirectly),
)

fun parseTargetLink(rs: ResultSet) = BlastTargetLink(
  jobID      = HashID(rs.getBytes(Column.MultiBlastJobToTargets.JobID)),
  organism   = rs.getString(Column.MultiBlastJobToTargets.Organism),
  targetFile = rs.getString(Column.MultiBlastJobToTargets.TargetFile)
)

fun parseJobLink(rs: ResultSet) = BlastJobLink(
  childJobID  = HashID(rs.getBytes(Column.MultiBlastJobToJobs.JobID)),
  parentJobID = HashID(rs.getBytes(Column.MultiBlastJobToJobs.ParentDigest)),
  position    = rs.getInt(Column.MultiBlastJobToJobs.Position)
)

fun queryToFile(queryClob: Clob): File {
  val queryFile = File("/tmp/" + UUID.randomUUID())

  if (!queryFile.createNewFile()) {
    queryClob.free()
    throw Exception("Failed to create tmp file for job query.")
  }

  queryFile.deleteOnExit()

  queryClob.asciiStream.transferTo(FileOutputStream(queryFile))
  queryClob.free()

  return queryFile
}