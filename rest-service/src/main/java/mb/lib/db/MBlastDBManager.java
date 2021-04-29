package mb.lib.db;

import java.sql.Connection;

import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class MBlastDBManager implements AutoCloseable
{
  private final Connection con;

  public MBlastDBManager() throws Exception {
    con = DbManager.userDatabase().getDataSource().getConnection();
  }

  protected Connection getConnection() {
    return con;
  }

  @Override
  public void close() throws Exception {
    con.close();
  }
}
