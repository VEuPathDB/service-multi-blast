package mb.lib.db.select;

import java.sql.ResultSet;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.SQL;

public class SelectUserIsLinkedToJob
{
  private final DataSource ds;
  private final long userID;
  private final byte[] jobHash;

  public SelectUserIsLinkedToJob(DataSource ds, long userID, byte[] jobHash) {
    this.ds      = ds;
    this.userID  = userID;
    this.jobHash = jobHash;
  }

  public boolean run() throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.MultiBlastUsers.UserIsLinked,
      ds::getConnection,
      ResultSet::next,
      ps -> {
        ps.setLong(1, userID);
        ps.setBytes(2, jobHash);
      }
    ).execute().getValue();
  }
}
