package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;

import static mb.lib.db.constants.SQL.Select.Users.IsGuest;

public class SelectUserIsGuest
{
  private final Connection con;
  private final long       userID;

  public SelectUserIsGuest(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
  }

  public boolean run() throws Exception {
    return new BasicPreparedReadQuery<>(IsGuest, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  public boolean parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return false;
    return rs.getBoolean(Column.Users.IsGuest);
  }

  public void prepare(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
  }
}
