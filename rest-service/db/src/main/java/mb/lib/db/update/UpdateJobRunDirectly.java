package mb.lib.db.update;

import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.db.constants.SQL;

public class UpdateJobRunDirectly
{
  private final DataSource ds;
  private final boolean    runDirectly;
  private final long       userID;
  private final byte[]     jobHash;

  public UpdateJobRunDirectly(DataSource ds, boolean runDirectly, long userID, byte[] jobHash) {
    this.ds          = ds;
    this.runDirectly = runDirectly;
    this.userID      = userID;
    this.jobHash     = jobHash;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(
      SQL.Update.MultiBlastUsers.RunDirectly,
      ds::getConnection,
      ps -> {
        ps.setBoolean(1, runDirectly);
        ps.setLong(2, userID);
        ps.setBytes(3, jobHash);
      }
    ).execute();
  }
}
