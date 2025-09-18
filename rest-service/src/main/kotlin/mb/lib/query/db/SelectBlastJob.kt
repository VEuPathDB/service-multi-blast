package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class SelectBlastJob(private val con: Connection, private val jobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      *
    FROM
      multiblast.multiblast_jobs
    WHERE
      job_digest = ?
    """
  }

  fun run() = BasicPreparedReadQuery(Query, con, this::parse, this::prep).execute().value

  fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)

  fun parse(rs: ResultSet) = if (!rs.next()) null else parseBlastRow(rs)
}
