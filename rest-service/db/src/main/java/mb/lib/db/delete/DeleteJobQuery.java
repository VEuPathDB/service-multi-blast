package mb.lib.db.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;

/**
 * Query to delete a job query by job ID (hash).
 */
public class DeleteJobQuery
{
  private final byte[]     jobID;
  private final Connection con;

  public DeleteJobQuery(Connection con, byte[] jobID) {
    this.jobID = jobID;
    this.con   = con;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Delete.MultiBlastJobs.ByID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID);
  }
}