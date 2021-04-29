package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.model.FullUserJobRow;
import mb.lib.db.model.impl.FullUserJobRowImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobs.FullUserRow;

public class SelectFullUserJob
{
  private final HashID jobID;
  private final long   userID;
  private final Connection con;

  public SelectFullUserJob(Connection con, HashID jobID, long userID) {
    this.con    = con;
    this.jobID  = jobID;
    this.userID = userID;
  }

  public Optional<FullUserJobRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(FullUserRow, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  private Optional<FullUserJobRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new FullUserJobRowImpl(
      jobID,
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.JobConfig),
      Util.queryToFile(rs),
      rs.getString(Column.MultiBlastJobs.ProjectID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status)),
      rs.getLong(Column.MultiBlastUsers.UserId),
      rs.getString(Column.MultiBlastUsers.Description),
      rs.getLong(Column.MultiBlastUsers.MaxDownloadSize),
      rs.getBoolean(Column.MultiBlastUsers.RunDirectly)
    ));
  }

  private void prepare(PreparedStatement ps) throws Exception{
    ps.setBytes(1, jobID.bytes());
    ps.setLong(2, userID);
  }
}