package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;

public class DeleteUserReportLink
{
  private static final String Query = """
    DELETE FROM
      userlogins5.multiblast_users_to_fmt_jobs
    WHERE
      report_digest = ?
      AND user_id = ?
    """;

  private final Connection con;
  private final HashID reportID;
  private final long userID;

  public DeleteUserReportLink(Connection con, HashID reportID, long userID) {
    this.con      = con;
    this.reportID = reportID;
    this.userID   = userID;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, reportID.bytes());
    ps.setLong(2, userID);
  }
}
