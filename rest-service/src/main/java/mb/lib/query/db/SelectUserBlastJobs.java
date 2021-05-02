package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.query.model.UserBlastRow;

public class SelectUserBlastJobs
{
  private static final String Query = """
    SELECT
      a.*
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
    """;

  private final Connection con;
  private final long       userID;

  public SelectUserBlastJobs(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
  }

  public List<UserBlastRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::parseUserBlastRow, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
  }
}
