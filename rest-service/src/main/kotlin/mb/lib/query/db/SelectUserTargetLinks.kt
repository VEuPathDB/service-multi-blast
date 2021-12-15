package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.model.HashID
import mb.lib.query.model.BlastTargetLink
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class SelectUserTargetLinks(
  private val con: Connection,
  private val userID: Long,
) {
  companion object {
    private const val Query = """
    SELECT
      a.*
    FROM
      userlogins5.multiblast_job_to_targets a
      INNER JOIN userlogins5.multiblast_users mu
        ON a.job_digest = mu.job_digest
    WHERE
      mu.user_id = ?
    """
  }

  fun run(): Map<HashID, List<BlastTargetLink>> {
    return BasicPreparedReadQuery(Query, con, this::parse, this::prep).execute().getValue()
  }

  private fun parse(rs: ResultSet): Map<HashID, List<BlastTargetLink>> {
    val out = HashMap<HashID, List<BlastTargetLink>>()

    while (rs.next()) {
      val tmp = parseTargetLink(rs)

      (out.computeIfAbsent(tmp.jobID!!) { ArrayList() } as ArrayList)
        .add(tmp)
    }

    return out
  }

  private fun prep(ps: PreparedStatement) {
    ps.setLong(1, userID)
  }
}
