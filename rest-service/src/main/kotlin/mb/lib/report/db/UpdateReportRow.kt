package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.report.model.ReportRow
import java.sql.Connection
import java.sql.PreparedStatement

data class UpdateReportRow(val con: Connection, val row: ReportRow) {
  companion object {
    private const val Query = """
    UPDATE
      userlogins5.multiblast_fmt_jobs
    SET
      queue_id = ?
    , status   = ?
    WHERE
      report_digest = ?
    """
  }

  fun run() {
    BasicPreparedVoidQuery(Query, con, this::prepare).execute()
  }

  private fun prepare(ps: PreparedStatement) {
    ps.setInt(1, row.queueID)
    ps.setString(2, row.status.value)
    ps.setBytes(3, row.reportID.bytes)
  }
}
