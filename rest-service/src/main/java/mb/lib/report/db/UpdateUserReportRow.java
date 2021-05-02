package mb.lib.report.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.report.model.UserReportRow;

public class UpdateUserReportRow
{
  private static final String Query = """
    UPDATE
      userlogins5.multiblast_users_to_fmt_jobs
    SET
      description = ?
    WHERE
      report_digest = ?
      AND user_id = ?
    """;

  private final Connection con;
  private final UserReportRow row;

  public UpdateUserReportRow(Connection con, UserReportRow row) {
    this.con = con;
    this.row = row;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setString(1, row.getDescription());
    ps.setBytes(2, row.getReportID().bytes());
    ps.setLong(3, row.getUserID());
  }
}
