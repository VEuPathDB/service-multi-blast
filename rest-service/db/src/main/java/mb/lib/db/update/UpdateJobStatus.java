package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.DBJobStatus;

public class UpdateJobStatus
{
  private final Connection con;
  private final byte[]     jobID;
  private final DBJobStatus status;

  public UpdateJobStatus(Connection con, byte[] jobID, DBJobStatus status) {
    this.con    = con;
    this.jobID  = jobID;
    this.status = status;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Update.MultiBlastJobs.Status, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setString(1, status.value);
    ps.setBytes(2, jobID);
  }
}
