package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

public class UpdateJobStatus
{
  private static final String Query = """
    UPDATE
      userlogins5.multiblast_jobs
    SET
      status = ?
    , queue_id = ?
    WHERE
      job_digest = ?
    """;

  private final Connection con;
  private final HashID     jobID;
  private final int        queueID;
  private final JobStatus  status;

  public UpdateJobStatus(Connection con, HashID jobID, int queueID, JobStatus status) {
    this.con     = con;
    this.jobID   = jobID;
    this.queueID = queueID;
    this.status  = status;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setString(1, status.getValue());
    ps.setInt(2, queueID);
    ps.setBytes(3, jobID.bytes());
  }
}
