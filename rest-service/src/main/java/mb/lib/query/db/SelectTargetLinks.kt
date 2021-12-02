package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.model.HashID
import mb.lib.query.model.BlastTargetLink
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectTargetLinks(private val con: Connection, private val jobID: HashID) {
  companion object {
    private const val Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_job_to_targets
    WHERE
      job_digest = ?
    """
  }

  fun run(): List<BlastTargetLink> =
    BasicPreparedListReadQuery(Query, con, ::parseTargetLink, this::prep).execute().value

  private fun prep(ps: PreparedStatement) = ps.setBytes(1, jobID.bytes)
}
