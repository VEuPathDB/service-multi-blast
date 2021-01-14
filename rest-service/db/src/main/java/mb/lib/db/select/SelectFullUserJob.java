package mb.lib.db.select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FullUserJobRow;
import mb.lib.db.model.JobRowFactory;

public class SelectFullUserJob
{
  private final byte[]     jobID;
  private final long       userID;
  private final DataSource ds;

  public SelectFullUserJob(DataSource ds, byte[] jobID, long userID) {
    this.ds     = ds;
    this.jobID  = jobID;
    this.userID = userID;
  }

  public Optional<FullUserJobRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.MultiBlastJobs.FullUserRow,
      ds::getConnection,
      this::parse,
      this::prepare
    ).execute().getValue();
  }

  private Optional<FullUserJobRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(JobRowFactory.newFullUserJobRow(
      jobID,
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.JobConfig),
      rs.getLong(Column.MultiBlastUsers.UserId),
      rs.getString(Column.MultiBlastUsers.Description)
    ));
  }

  private void prepare(PreparedStatement ps) throws Exception{
    ps.setBytes(1, jobID);
    ps.setLong(2, userID);
  }
}
