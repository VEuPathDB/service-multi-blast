package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.formatter.model.ReportRow;
import mb.lib.util.JSON;

public class InsertReportRow
{
  private static final String Query = """
    INSERT INTO
      userlogins5.multiblast_fmt_jobs (
        report_digest
      , job_digest
      , status
      , config
      , queue_id
      )
    VALUES
      (?, ?, ?, ?, ?)
    """;

  private final Connection con;
  private final ReportRow row;

  public InsertReportRow(Connection con, ReportRow row) {
    this.con = con;
    this.row = row;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, row.getReportID().bytes());
    ps.setBytes(2, row.getJobID().bytes());
    ps.setString(3, row.getStatus().getValue());
    ps.setString(4, JSON.stringify(row.getConfig()));
    ps.setInt(5, row.getQueueID());
  }
}
