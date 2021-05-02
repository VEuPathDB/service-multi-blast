package mb.lib.report.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.report.model.ReportRow;
import mb.lib.model.HashID;

public class SelectReportRow
{
  private static final String Query =
    """
    SELECT
      *
    FROM
      userlogins5.multiblast_fmt_jobs
    WHERE
      report_digest = ?
    """;

  private final Connection con;
  private final HashID reportID;

  public SelectReportRow(Connection con, HashID reportID) {
    this.con      = con;
    this.reportID = reportID;
  }

  public Optional<ReportRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, this::parse, this::prepare).execute().getValue();
  }

  private Optional<ReportRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(Util.rs2Row(rs));
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, reportID.bytes());
  }
}
