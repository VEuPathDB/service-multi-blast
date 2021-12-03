package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.model.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectBlastQuery(private val con: Connection, private val jobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      query
    FROM
      userlogins5.multiblast_jobs
    WHERE
      job_digest = ?
    """
  }

  fun run(): String? =
    BasicPreparedReadQuery<String>(Query, con, { if (it.next()) it.getString(1) else null }, this::prep).execute().value

  private fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)
}
