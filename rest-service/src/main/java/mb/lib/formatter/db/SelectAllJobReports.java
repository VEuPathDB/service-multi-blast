package mb.lib.formatter.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.formatter.model.ReportRow;
import mb.lib.model.HashID;

public class SelectAllJobReports
{
  private static final String Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_fmt_jobs
    WHERE
      job_digest = ?
    """;

  private final Connection con;
  private final HashID jobID;

  public SelectAllJobReports(Connection con, HashID jobID) {
    this.con   = con;
    this.jobID = jobID;
  }

  public List<ReportRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::rs2Row, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
  }
}
