package mb.lib.db.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.UserRow;

public class InsertUserQuery
{
  private final Connection connection;
  private final UserRow    user;

  public InsertUserQuery(Connection connection, UserRow user) {
    this.connection = connection;
    this.user       = user;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Insert.User, connection, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws SQLException {
    ps.setBytes(1, user.jobHash());
    ps.setLong(2, user.userID());
    ps.setString(3, user.description());
    ps.setBytes(4, user.parentHash());
    ps.setLong(5, user.maxDownloadSize());
  }

  public static void execute(Connection con, UserRow user) throws Exception {
    new InsertUserQuery(con, user).run();
  }
}
