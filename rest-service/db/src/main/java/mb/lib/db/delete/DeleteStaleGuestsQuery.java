package mb.lib.db.delete;

import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicStatementWriteQuery;
import static mb.lib.db.constants.SQL.Delete;

public class DeleteStaleGuestsQuery
{
  private final DataSource ds;

  public DeleteStaleGuestsQuery(DataSource ds) {
    this.ds = ds;
  }

  public void run() throws Exception {
    new BasicStatementWriteQuery(Delete.MultiBlastUsers.StaleGuests, ds::getConnection).execute();
  }
}
