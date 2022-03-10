package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.report.model.UserReportRow
import java.sql.Connection
import java.sql.PreparedStatement

data class UpdateUserReportRow(val con: Connection, val row: UserReportRow) {
  companion object {
    private const val Query = """
    UPDATE
      userlogins5.multiblast_users_to_fmt_jobs
    SET
      description = ?
    WHERE
      report_digest = ?
      AND user_id = ?
    """
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prep).execute()

  private fun prep(ps: PreparedStatement) {
    ps.setString(1, row.description)
    ps.setBytes(2, row.reportID.bytes)
    ps.setLong(3, row.userID)
  }
}
