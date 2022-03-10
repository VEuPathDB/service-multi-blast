package mb.lib.report.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

data class SelectReportRow(val con: Connection, val reportID: HashID) {
  companion object {
    private val Query =
    """
    SELECT
      *
    FROM
      userlogins5.multiblast_fmt_jobs
    WHERE
      report_digest = ?
    """
  }

  fun run() = BasicPreparedReadQuery(Query, con, this::parse, this::prepare).execute().value!!

  private fun parse(rs: ResultSet) = if (!rs.next()) Optional.empty() else Optional.of(rs2Row(rs))

  private fun prepare(ps: PreparedStatement) = ps.setBytes(1, reportID.bytes)
}
