package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.UserBlastRow;

public class SelectUserBlastJob
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
      a.job_digest = ?
      AND b.user_id = ?
    """;

  private final HashID jobID;
  private final long   userID;
  private final Connection con;

  public SelectUserBlastJob(Connection con, HashID jobID, long userID) {
    this.con    = con;
    this.jobID  = jobID;
    this.userID = userID;
  }

  public Optional<UserBlastRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  private Optional<UserBlastRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(Util.parseUserBlastRow(rs));
  }

  private void prepare(PreparedStatement ps) throws Exception{
    ps.setBytes(1, jobID.bytes());
    ps.setLong(2, userID);
  }
}
