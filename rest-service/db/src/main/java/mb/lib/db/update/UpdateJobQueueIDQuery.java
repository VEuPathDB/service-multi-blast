package mb.lib.db.update;

import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class UpdateJobQueueIDQuery
{
  private final byte[] jobID;
  private final int queueID;

  public UpdateJobQueueIDQuery(byte[] jobID, int queueID) {
    this.jobID   = jobID;
    this.queueID = queueID;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(
      SQL.Update.MultiBlastJobs.QueueID,
      DbManager.userDatabase().getDataSource()::getConnection,
      this::prepare
    ).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setInt(1, queueID);
    ps.setBytes(2, jobID);
  }
}
