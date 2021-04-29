package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.model.HashID;

/**
 * Query for updating the external queue ID for a target job.
 */
public class UpdateJobQueueIDQuery
{
  private final Connection con;
  private final byte[]     jobID;
  private final int        queueID;

  public UpdateJobQueueIDQuery(Connection con, HashID jobID, int queueID) {
    this.con     = con;
    this.jobID   = jobID.bytes();
    this.queueID = queueID;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Update.MultiBlastJobs.QueueID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setInt(1, queueID);
    ps.setBytes(2, jobID);
  }
}