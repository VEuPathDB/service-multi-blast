package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mb.lib.db.constants.SQL.Update.MultiBlastFmtJobs.Status;

public class UpdateFormatJobStatus
{
  private static final Logger Log = LogManager.getLogger(UpdateFormatJobStatus.class);

  private final Connection con;
  private final HashID jobID;
  private final HashID reportID;
  private final JobStatus status;

  public UpdateFormatJobStatus(Connection con, HashID jobID, HashID reportID, JobStatus status) {
    Log.trace("::new(con={}, jobID={}, reportID={}, status={})", con, jobID, reportID, status);
    this.con      = con;
    this.jobID    = jobID;
    this.reportID = reportID;
    this.status   = status;
  }

  public void run() throws Exception {
    Log.trace("#run()");
    new BasicPreparedVoidQuery(Status, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    Log.trace("#prepare(ps={})", ps);
    ps.setString(1, status.getValue());
    ps.setBytes(2, jobID.bytes());
    ps.setBytes(3, reportID.bytes());
  }
}
