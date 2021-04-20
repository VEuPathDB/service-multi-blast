package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.api.model.reports.ReportRequest;
import mb.lib.db.constants.Column;
import mb.lib.db.model.FormatJobStatus;
import mb.lib.db.model.impl.FormatJobStatusImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.util.JSON;

import static mb.lib.db.constants.SQL.Select.MultiBlastFormatJobs.ByJobID;

public class SelectReportJob
{
  private final Connection con;
  private final HashID     jobID;
  private final HashID     reportID;

  public SelectReportJob(Connection con, HashID jobID, HashID reportID) {
    this.con      = con;
    this.jobID    = jobID;
    this.reportID = reportID;
  }

  public Optional<FormatJobStatus> run() throws Exception {
    return new BasicPreparedReadQuery<>(ByJobID, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
    ps.setBytes(2, reportID.bytes());
  }

  public Optional<FormatJobStatus> parse(ResultSet rs) throws Exception {
    if (rs.next())
      return Optional.empty();

    return Optional.of(new FormatJobStatusImpl(
      jobID,
      reportID,
      JSON.parse(rs.getString(Column.MultiBlastFormatJobs.Config), ReportRequest.class),
      rs.getInt(Column.MultiBlastFormatJobs.QueueID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastFormatJobs.Status))
    ));
  }
}
