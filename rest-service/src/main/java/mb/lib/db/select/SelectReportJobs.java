package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.api.model.reports.ReportRequest;
import mb.lib.db.constants.Column;
import mb.lib.db.model.FormatJobStatus;
import mb.lib.db.model.impl.FormatJobStatusImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.util.JSON;

import static mb.lib.db.constants.SQL.Select.MultiBlastFormatJobs.ByJobID;

public class SelectReportJobs
{
  private final Connection con;
  private final HashID     jobID;
  private final long       userID;

  public SelectReportJobs(Connection con, HashID jobID, long userID) {
    this.con    = con;
    this.jobID  = jobID;
    this.userID = userID;
  }

  public List<FormatJobStatus> run() throws Exception {
    return new BasicPreparedListReadQuery<>(ByJobID, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  public void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
    ps.setLong(2, userID);
  }

  public FormatJobStatus parse(ResultSet rs) throws Exception {
    return new FormatJobStatusImpl(
      jobID,
      new HashID(rs.getBytes(Column.MultiBlastFormatJobs.ReportDigest)),
      userID,
      JSON.parse(rs.getString(Column.MultiBlastFormatJobs.Config), ReportRequest.class),
      rs.getInt(Column.MultiBlastFormatJobs.QueueID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastFormatJobs.Status))
    );
  }
}
