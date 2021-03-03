package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.model.UserRow;
import mb.lib.db.model.impl.UserRowImpl;

import static mb.lib.db.constants.SQL.Select.MultiBlastUsers.ByID;

public class SelectUser
{
  private final Connection con;
  private final long       userID;
  private final byte[]     jobID;

  public SelectUser(Connection con, long userID, byte[] jobID) {
    this.con    = con;
    this.userID = userID;
    this.jobID  = jobID;
  }

  public Optional<UserRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(ByID, con, this::parse, this::prepare).execute().getValue();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
    ps.setBytes(2, jobID);
  }

  private Optional<UserRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new UserRowImpl(
      jobID,
      userID,
      rs.getString(Column.MultiBlastUsers.Description),
      rs.getLong(Column.MultiBlastUsers.MaxDownloadSize),
      rs.getBoolean(Column.MultiBlastUsers.RunDirectly)
    ));
  }
}
