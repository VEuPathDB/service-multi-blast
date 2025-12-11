package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.query.model.BlastJobLink
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectChildBlastLinks(
  private val con: Connection,
  private val parentJobID: HashID
) {
  companion object {
    // language=postgresql
    private const val Query = """
    SELECT
      *
    FROM
      multiblast.multiblast_job_to_jobs
    WHERE
      parent_digest = ?
    ORDER BY
      position ASC
    """
  }

  fun run(): List<BlastJobLink> = BasicPreparedListReadQuery(Query, con, ::parseJobLink, this::prep).execute().value

  private fun prep(ps: PreparedStatement) = ps.setBytes(1, parentJobID.bytes)
}
