package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.model.HashID
import mb.lib.query.model.BlastRow
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

data class SelectBlastJob(private val con: Connection, private val jobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_jobs
    WHERE
      job_digest = ?
    """
  }

  fun run() = BasicPreparedReadQuery(Query, con, this::parse, this::prep).execute().value

  fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)

  fun parse(rs: ResultSet) = if (!rs.next()) null else parseBlastRow(rs)
}
