package mb.lib.db.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Delete.MultiBlastJobToJobs.ByID;

public class DeleteJobToJobLinks
{
  private final Connection con;
  private final byte[]     jobID;

  public DeleteJobToJobLinks(Connection con, HashID jobID) {
    this.con   = con;
    this.jobID = jobID.bytes();
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(ByID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID);
  }
}
