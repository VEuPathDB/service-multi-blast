package mb.lib.report.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.report.model.ReportRow;

public class UpdateReportRow
{
  private static final String Query = """
    UPDATE
      userlogins5.multiblast_fmt_jobs
    SET
      queue_id = ?
    , status   = ?
    WHERE
      report_digest = ?
    """;

  private final Connection con;
  private final ReportRow row;

  public UpdateReportRow(Connection con, ReportRow row) {
    this.con = con;
    this.row = row;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setInt(1, row.getQueueID());
    ps.setString(2, row.getStatus().getValue());
    ps.setBytes(3, row.getReportID().bytes());
  }
}
