package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.model.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class DeleteUserReportLink(
  val con: Connection,
  val reportID: HashID,
  val userID: Long
) {
  companion object {
    private const val Query = """
    DELETE FROM
      userlogins5.multiblast_users_to_fmt_jobs
    WHERE
      report_digest = ?
      AND user_id = ?
    """
  }
  fun run() = BasicPreparedVoidQuery(Query, con, this::prepare).execute()

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, reportID.bytes)
    ps.setLong(2, userID)
  }
}
