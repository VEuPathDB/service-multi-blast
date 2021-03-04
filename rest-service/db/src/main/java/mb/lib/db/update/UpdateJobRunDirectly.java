package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;

import static mb.lib.db.constants.SQL.Update.MultiBlastUsers.RunDirectly;

/**
 * Query to update the 'run directly' flag for a specific user-to-job link.
 */
public class UpdateJobRunDirectly
{
  private final Connection con;
  private final boolean    runDirectly;
  private final long       userID;
  private final byte[]     jobHash;

  public UpdateJobRunDirectly(Connection con, boolean runDirectly, long userID, byte[] jobHash) {
    this.con         = con;
    this.runDirectly = runDirectly;
    this.userID      = userID;
    this.jobHash     = jobHash;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(RunDirectly, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBoolean(1, runDirectly);
    ps.setLong(2, userID);
    ps.setBytes(3, jobHash);
  }
}
