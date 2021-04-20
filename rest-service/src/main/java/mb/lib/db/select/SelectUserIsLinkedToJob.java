package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Select.MultiBlastUsers.UserIsLinked;

public class SelectUserIsLinkedToJob
{
  private final Connection con;
  private final long       userID;
  private final byte[]     jobHash;

  public SelectUserIsLinkedToJob(Connection con, long userID, HashID jobHash) {
    this.con     = con;
    this.userID  = userID;
    this.jobHash = jobHash.bytes();
  }

  public boolean run() throws Exception {
    return new BasicPreparedReadQuery<>(UserIsLinked, con, ResultSet::next, this::prepare)
      .execute()
      .getValue();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
    ps.setBytes(2, jobHash);
  }
}
