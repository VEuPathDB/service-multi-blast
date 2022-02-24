package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery
import mb.lib.report.model.ReportRow
import mb.lib.util.jsonStringify
import java.sql.Connection
import java.sql.PreparedStatement

data class InsertReportRow(
  val con: Connection,
  val row: ReportRow
) {
  companion object {
    @Suppress("SpellCheckingInspection")
    private const val Query = """
    INSERT INTO
      userlogins5.multiblast_fmt_jobs (
        report_digest
      , job_digest
      , status
      , config
      , queue_id
      )
    SELECT ?, ?, ?, ?, ?
    FROM dual
    WHERE NOT EXISTS (
      SELECT *
      FROM userlogins5.multiblast_fmt_jobs
      WHERE report_digest = ?
    )
    """
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prepare).execute()

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, row.reportID.bytes)
    ps.setBytes(2, row.jobID.bytes)
    ps.setString(3, row.status.value)
    ps.setString(4, row.config.jsonStringify())
    ps.setInt(5, row.queueID)
    ps.setBytes(6, row.reportID.bytes)
  }
}
