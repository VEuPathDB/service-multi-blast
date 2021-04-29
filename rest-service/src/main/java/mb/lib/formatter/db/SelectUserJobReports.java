package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.formatter.model.UserReportRow;
import mb.lib.model.HashID;

public class SelectUserJobReports
{
  private static final String Query = """
    SELECT
      a.*
    , b.user_id
    , b.description
    FROM
      userlogins5.multiblast_fmt_jobs a
      INNER JOIN userlogins5.multiblast_users_to_fmt_jobs b
        ON a.report_digest = b.report_digest
    WHERE
      job_digest = ?
      AND user_id = ?
    """;

  private final Connection con;
  private final HashID     jobID;
  private final long       userID;

  public SelectUserJobReports(Connection con, HashID jobID, long userID) {
    this.con   = con;
    this.jobID = jobID;
    this.userID = userID;
  }

  public List<UserReportRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::rs2UserRow, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
    ps.setLong(2, userID);
  }
}
