package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.model.JobStatus
import mb.lib.report.model.UserReportRow
import mb.lib.util.convertReportConfig
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.OffsetDateTime
import java.util.*

data class SelectUserReportRow(val con: Connection, val reportID: HashID, val userID: Long) {
  companion object {
    private const val Query =
    """
      SELECT
        a.*
      , b.user_id
      , b.description
      FROM
        userlogins5.multiblast_fmt_jobs a
        INNER JOIN userlogins5.multiblast_users_to_fmt_jobs b
          ON a.report_digest = b.report_digest
      WHERE
        a.report_digest = ?
        AND user_id = ?
      """
  }
  fun run() = BasicPreparedReadQuery(Query, con, this::parse, this::prepare).execute().value

  fun parse(rs: ResultSet): UserReportRow? {
    if (!rs.next())
      return null

    return UserReportRow(
      reportID,
      HashID(rs.getBytes(Column.FormatJob.JobID)),
      JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
      convertReportConfig(rs.getString(Column.FormatJob.Config)),
      rs.getInt(Column.FormatJob.QueueID),
      rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime::class.java),
      rs.getLong(Column.UserLink.UserID),
      rs.getString(Column.UserLink.Description)
    )
  }

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, reportID.bytes)
    ps.setLong(2, userID)
  }
}
