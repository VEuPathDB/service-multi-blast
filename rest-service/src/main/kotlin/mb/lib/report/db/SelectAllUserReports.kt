package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.report.model.UserReportRow
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectAllUserReports(val con: Connection, val userID: Long) {
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
      b.user_id = ?
    """
  }
  fun run(): List<UserReportRow> = BasicPreparedListReadQuery(Query, con, ::rs2UserRow, this::prep).execute().value

  fun prep(ps: PreparedStatement) = ps.setLong(1, userID)
}