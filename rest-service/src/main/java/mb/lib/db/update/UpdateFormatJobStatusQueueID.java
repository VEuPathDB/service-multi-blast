package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.db.model.FormatJobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mb.lib.db.constants.SQL.Update.MultiBlastFmtJobs.StatusQueueID;

public class UpdateFormatJobStatusQueueID
{
  private static final Logger Log = LogManager.getLogger(UpdateFormatJobStatusQueueID.class);

  private final Connection      con;
  private final FormatJobStatus stat;

  public UpdateFormatJobStatusQueueID(Connection con, FormatJobStatus stat) {
    Log.trace("::new(con={}, stat={})", con, stat);
    this.con  = con;
    this.stat = stat;
  }

  public void run() throws Exception {
    Log.trace("#run()");
    new BasicPreparedVoidQuery(StatusQueueID, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    Log.trace("#prepare(ps={})", ps);
    ps.setString(1, stat.status().getValue());
    ps.setInt(2, stat.queueID());
    ps.setBytes(3, stat.jobID().bytes());
    ps.setBytes(4, stat.reportID().bytes());
    ps.setLong(5, stat.userID());
  }
}
