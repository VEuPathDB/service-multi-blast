package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.query.model.JobLinkCollection
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class SelectUserJobLinks(
  private val con: Connection,
  private val userID: Long,
) {
  companion object {
    private const val Query = """
    SELECT
      a.*
    FROM
      multiblast.multiblast_job_to_jobs a
      INNER JOIN multiblast.multiblast_users b
        ON a.job_digest = b.job_digest
    WHERE
      user_id = ?
    """
  }

  fun run(): JobLinkCollection {
    return BasicPreparedReadQuery(Query, con, this::parse, this::prep).execute().value
  }

  private fun parse(rs: ResultSet): JobLinkCollection {
    val output = JobLinkCollection()

    while (rs.next()) {
      val link = parseJobLink(rs)

      (output.byParentID.computeIfAbsent(link.parentJobID!!) { ArrayList() } as ArrayList).add(link)
      (output.byChildID.computeIfAbsent(link.childJobID!!) { ArrayList() } as ArrayList).add(link)
    }

    return output
  }

  private fun prep(ps: PreparedStatement) {
    ps.setLong(1, userID)
  }
}
