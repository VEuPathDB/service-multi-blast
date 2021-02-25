package mb.lib.db.update;

import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.DBJobStatus;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class UpdateJobStatus
{
  private final byte[]      jobID;
  private final DBJobStatus status;

  public UpdateJobStatus(byte[] jobID, DBJobStatus status) {
    this.jobID   = jobID;
    this.status = status;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(
      SQL.Update.MultiBlastJobs.Status,
      DbManager.userDatabase().getDataSource()::getConnection,
      this::prepare
    ).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setString(1, status.value);
    ps.setBytes(2, jobID);
  }
}
