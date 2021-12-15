package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.model.HashID
import mb.lib.query.model.BlastJobLink
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectParentJobLinks(private val con: Connection, private val childJobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_job_to_jobs
    WHERE
      job_digest = ?
    ORDER BY
      position ASC
    """
  }

  fun run(): List<BlastJobLink> = BasicPreparedListReadQuery(Query, con, ::parseJobLink, this::prep).execute().value

  private fun prep(ps: PreparedStatement) = ps.setBytes(1, childJobID.bytes)
}
