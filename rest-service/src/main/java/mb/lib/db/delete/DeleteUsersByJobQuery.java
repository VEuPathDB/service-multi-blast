package mb.lib.db.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.model.HashID;

public class DeleteUsersByJobQuery
{
  private final byte[]     jobID;
  private final Connection con;

  public DeleteUsersByJobQuery(HashID jobID, Connection con) {
    this.jobID = jobID.bytes();
    this.con   = con;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Delete.MultiBlastUsers.ByJobID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID);
  }
}