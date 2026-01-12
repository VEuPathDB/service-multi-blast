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
    // language=postgresql
    private const val Query = """
    INSERT INTO
      multiblast.multiblast_fmt_jobs (
        report_digest
      , job_digest
      , status
      , config
      , queue_id
      )
    VALUES
      (?, ?, ?, ?, ?)
    ON CONFLICT DO NOTHING
    """
  }

  fun run() = BasicPreparedVoidQuery(Query, con, this::prepare).execute()

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, row.reportID.bytes)
    ps.setBytes(2, row.jobID.bytes)
    ps.setString(3, row.status.value)
    ps.setString(4, row.config.jsonStringify())
    ps.setInt(5, row.queueID)
  }
}
