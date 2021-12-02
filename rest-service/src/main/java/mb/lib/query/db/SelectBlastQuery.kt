package mb.lib.query.db

import io.vulpine.lib.query.util.RowParser
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.model.HashID
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

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

  fun run(): Optional<String> =
    BasicPreparedReadQuery<Optional<String>>(Query, con, { RowParser.optionalString(it) }, this::prep).execute().value

  private fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)
}
