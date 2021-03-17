package mb.lib.db.delete;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;

import static mb.lib.db.constants.SQL.Delete.MultiBlastJobToTargets.ByID;

public class DeleteJobToTargetLinks
{
  private final Connection con;
  private final byte[]     jobID;

  public DeleteJobToTargetLinks(Connection con, byte[] jobID) {
    this.con   = con;
    this.jobID = jobID;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(ByID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID);
  }
}