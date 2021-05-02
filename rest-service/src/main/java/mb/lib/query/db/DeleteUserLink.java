package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;

/**
 * A query that deletes a user to blast job link.
 */
public class DeleteUserLink
{
  private static final String Query = """
    DELETE FROM
      userlogins5.multiblast_users
    WHERE
      job_digest = ?
      AND user_id = ?
    """;

  private final Connection con;
  private final HashID     jobID;
  private final long       userID;

  public DeleteUserLink(Connection con, HashID jobID, long userID) {
    this.con    = con;
    this.jobID  = jobID;
    this.userID = userID;
  }

  /**
   * Execute the SQL query.
   */
  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  /**
   * Prepare the given statement with the stored values.
   *
   * @param ps SQL statement to prepare.
   */
  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
    ps.setLong(2, userID);
  }
}
