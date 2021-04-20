package mb.lib.db.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FormatJobStatus;
import mb.lib.model.HashID;
import mb.lib.util.JSON;

public class InsertFormatJob
{
  private final Connection      con;
  private final FormatJobStatus job;

  public InsertFormatJob(Connection con, FormatJobStatus stat) {
    this.con = con;
    this.job = stat;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(SQL.Insert.FmtJob, con, this::prepare).execute();
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, job.jobID().bytes());
    ps.setBytes(2, job.reportID().bytes());
    ps.setString(3, JSON.stringify(job.config()));
    ps.setInt(2, job.queueID());
    ps.setString(3, job.status().getValue());
  }
}
