package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.model.HashID
import mb.lib.report.model.ReportRow
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectAllJobReports(val con: Connection, val jobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_fmt_jobs
    WHERE
      job_digest = ?
    """
  }
  fun run(): List<ReportRow> = BasicPreparedListReadQuery(Query, con, ::rs2Row, this::prep).execute().value!!

  fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)
}
