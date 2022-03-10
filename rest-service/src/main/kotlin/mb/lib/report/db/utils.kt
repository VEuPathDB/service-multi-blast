package mb.lib.report.db;

import mb.lib.model.JobStatus
import mb.lib.report.model.ReportRow
import mb.lib.report.model.UserReportRow
import mb.lib.util.convertReportConfig
import org.veupathdb.lib.hash_id.HashID
import java.sql.ResultSet
import java.time.OffsetDateTime

fun rs2Row(rs: ResultSet) = ReportRow(
  HashID(rs.getBytes(Column.FormatJob.ReportID)),
  HashID(rs.getBytes(Column.FormatJob.JobID)),
  JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
  convertReportConfig(rs.getString(Column.FormatJob.Config)),
  rs.getInt(Column.FormatJob.QueueID),
  rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime::class.java)
)

fun rs2UserRow(rs: ResultSet) = UserReportRow(
  HashID(rs.getBytes(Column.FormatJob.ReportID)),
  HashID(rs.getBytes(Column.FormatJob.JobID)),
  JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
  convertReportConfig(rs.getString(Column.FormatJob.Config)),
  rs.getInt(Column.FormatJob.QueueID),
  rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime::class.java),
  rs.getLong(Column.UserLink.UserID),
  rs.getString(Column.UserLink.Description)
)
