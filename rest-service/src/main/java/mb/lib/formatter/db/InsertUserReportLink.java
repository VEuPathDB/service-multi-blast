package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.model.HashID;

public class InsertUserReportLink
{
  private static final String Query =
    """
      INSERT INTO
        userlogins5.multiblast_users_to_fmt_jobs (report_digest, user_id, description)
      VALUES
        (?, ?, ?)
      """;

  private final Connection con;
  private final HashID     reportID;
  private final long       userID;
  private final String     description;

  public InsertUserReportLink(
    Connection con,
    HashID reportID,
    long userID,
    String description
  ) {
    this.con         = con;
    this.reportID    = reportID;
    this.userID      = userID;
    this.description = description;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, reportID.bytes());
    ps.setLong(2, userID);
    ps.setString(3, description);
  }
}
