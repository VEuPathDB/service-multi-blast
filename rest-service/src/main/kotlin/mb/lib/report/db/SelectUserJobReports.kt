package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.model.HashID
import mb.lib.report.model.UserReportRow
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectUserJobReports(val con: Connection, val jobID: HashID, val userID: Long) {
  companion object {
    @Suppress("SpellCheckingInspection")
    private const val Query = """
    SELECT
      a.*
    , b.user_id
    , b.description
    FROM
      userlogins5.multiblast_fmt_jobs a
      INNER JOIN userlogins5.multiblast_users_to_fmt_jobs b
        ON a.report_digest = b.report_digest
    WHERE
      job_digest = ?
      AND user_id = ?
    """
  }
  fun run(): List<UserReportRow> = BasicPreparedListReadQuery(Query, con, ::rs2UserRow, this::prep).execute().value

  fun prep(ps: PreparedStatement) {
    ps.setBytes(1, jobID.bytes)
    ps.setLong(2, userID)
  }
}
