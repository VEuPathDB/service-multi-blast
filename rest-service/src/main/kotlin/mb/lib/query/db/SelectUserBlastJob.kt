package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery
import mb.lib.query.model.UserBlastRow
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

data class SelectUserBlastJob(
  private val con: Connection,
  private val jobID: HashID,
  private val userID: Long,
) {
  companion object {
    // language=oracle
    private const val Query = """
    SELECT
      a.job_digest
    , a.job_config
    , a.queue_id
    , a.project_id
    , a.status
    , a.created_on
    , a.delete_on
    , b.user_id
    , b.description
    , b.max_download_size
    , b.run_directly
    FROM
      userlogins5.multiblast_jobs a
      INNER JOIN userlogins5.multiblast_users b
        ON a.job_digest = b.job_digest
    WHERE
      a.job_digest = ?
      AND b.user_id = ?
    """
  }

  fun run(): UserBlastRow? = BasicPreparedReadQuery(Query, con, this::parse, this::prepare).execute().value

  private fun parse(rs: ResultSet) = if (!rs.next()) null else parseUserBlastRow(rs)

  private fun prepare(ps: PreparedStatement) {
    ps.setBytes(1, jobID.bytes)
    ps.setLong(2, userID)
  }
}
