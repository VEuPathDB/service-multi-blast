package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;

public class InsertBlastJobLink
{
  private static final String Query = """
    INSERT INTO
      userlogins5.multiblast_job_to_jobs (
        job_digest,
        parent_digest,
        position
      )
    VALUES
      (?, ?, (
        SELECT
          COALESCE(MAX(position), 0) + 1
        FROM
          userlogins5.multiblast_job_to_jobs
        WHERE
          parent_digest = ?
        )
      )
    """;

  private final Connection con;
  private final HashID     childID;
  private final HashID     parentID;

  public InsertBlastJobLink(Connection con, HashID childID, HashID parentID) {
    this.con      = con;
    this.childID  = childID;
    this.parentID = parentID;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, childID.bytes());
    ps.setBytes(2, parentID.bytes());
    ps.setBytes(3, parentID.bytes());
  }
}
