package mb.lib.report.db;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class InsertUserReportLink(
  val con: Connection,
  val reportID: HashID,
  val userID: Long,
  val description: String?
) {
  companion object {
    private const val Query =
    """
      INSERT INTO
        userlogins5.multiblast_users_to_fmt_jobs (report_digest, user_id, description)
      VALUES
        (?, ?, ?)
      """;
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prepare).execute()

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, reportID.bytes);
    ps.setLong(2, userID);
    ps.setString(3, description);
  }
}
