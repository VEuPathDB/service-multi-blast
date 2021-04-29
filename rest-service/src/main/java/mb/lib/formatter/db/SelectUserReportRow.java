package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.formatter.model.UserReportRow;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.util.BlastConv;

public class SelectUserReportRow
{
  private static final String Query =
    """
      SELECT
        a.*
      , b.user_id
      , b.description
      FROM
        userlogins5.multiblast_fmt_jobs a
        INNER JOIN userlogins5.multiblast_users_to_fmt_jobs b
          ON a.report_digest = b.report_digest
      WHERE
        report_digest = ?
        AND user_id = ?
      """;

  private final Connection con;
  private final HashID     reportID;
  private final long       userID;

  public SelectUserReportRow(Connection con, HashID reportID, long userID) {
    this.con      = con;
    this.reportID = reportID;
    this.userID   = userID;
  }

  public Optional<UserReportRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(
      Query,
      con,
      this::parse,
      this::prepare
    ).execute().getValue();
  }

  private Optional<UserReportRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new UserReportRow(
      reportID,
      new HashID(rs.getBytes(Column.FormatJob.JobID)),
      JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
      BlastConv.convertReportConfig(rs.getString(Column.FormatJob.Config)),
      rs.getInt(Column.FormatJob.QueueID),
      rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime.class),
      rs.getLong(Column.UserLink.UserID),
      rs.getString(Column.UserLink.Description)
    ));
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, reportID.bytes());
    ps.setLong(2, userID);
  }
}
