package mb.lib.db.delete;

import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicStatementWriteQuery;
import mb.lib.db.constants.SQL;

public class DeleteOrphanJobsQuery
{
  private final DataSource ds;

  public DeleteOrphanJobsQuery(DataSource ds) {
    this.ds = ds;
  }

  public void run() throws Exception {
    new BasicStatementWriteQuery(SQL.Delete.MultiBlastJobs.Orphaned, ds::getConnection).execute();
  }
}
