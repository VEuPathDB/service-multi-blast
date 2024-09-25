package mb.lib.query.db

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery
import mb.lib.query.model.UserBlastRow
import java.sql.Connection
import java.sql.PreparedStatement

data class SelectUserBlastJobs(
  private val con: Connection,
  private val userID: Long
) {
  companion object {
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
      b.user_id = ?
    """
  }

  fun run(): List<UserBlastRow> = BasicPreparedListReadQuery(Query, con, ::parseUserBlastRow, this::prep)
    .execute()
    .value!!

  fun prep(ps: PreparedStatement) = ps.setLong(1, userID)
}
