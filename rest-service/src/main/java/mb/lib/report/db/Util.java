package mb.lib.report.db;

import java.sql.ResultSet;
import java.time.OffsetDateTime;

import mb.lib.report.model.ReportRow;
import mb.lib.report.model.UserReportRow;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.util.BlastConv;

class Util
{
  static ReportRow rs2Row(ResultSet rs) throws Exception {
    return new ReportRow(
      new HashID(rs.getBytes(Column.FormatJob.ReportID)),
      new HashID(rs.getBytes(Column.FormatJob.JobID)),
      JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
      BlastConv.convertReportConfig(rs.getString(Column.FormatJob.Config)),
      rs.getInt(Column.FormatJob.QueueID),
      rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime.class)
    );
  }

  static UserReportRow rs2UserRow(ResultSet rs) throws Exception {
    return new UserReportRow(
      new HashID(rs.getBytes(Column.FormatJob.ReportID)),
      new HashID(rs.getBytes(Column.FormatJob.JobID)),
      JobStatus.unsafeFromString(rs.getString(Column.FormatJob.Status)),
      BlastConv.convertReportConfig(rs.getString(Column.FormatJob.Config)),
      rs.getInt(Column.FormatJob.QueueID),
      rs.getObject(Column.FormatJob.CreatedOn, OffsetDateTime.class),
      rs.getLong(Column.UserLink.UserID),
      rs.getString(Column.UserLink.Description)
    );
  }
}
