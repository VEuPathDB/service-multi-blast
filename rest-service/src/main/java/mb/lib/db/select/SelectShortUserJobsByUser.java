package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Collection;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.model.ShortUserJobRow;
import mb.lib.db.model.impl.ShortUserJobRowImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobs.ShortUserByUserID;

public class SelectShortUserJobsByUser
{
  private final Connection con;
  private final long       userID;

  public SelectShortUserJobsByUser(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
  }

  public Collection<ShortUserJobRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(ShortUserByUserID, con, this::parse, this::prep)
      .execute()
      .getValue();
  }

  void prep(PreparedStatement stmt) throws Exception {
    stmt.setLong(1, this.userID);
  }

  ShortUserJobRow parse(ResultSet rs) throws Exception {
    return new ShortUserJobRowImpl(
      new HashID(rs.getBytes(Column.MultiBlastJobs.JobDigest)),
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.ProjectID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status)),
      rs.getLong(Column.MultiBlastUsers.UserId),
      rs.getString(Column.MultiBlastUsers.Description),
      rs.getLong(Column.MultiBlastUsers.MaxDownloadSize),
      rs.getBoolean(Column.MultiBlastUsers.RunDirectly)
    );
  }
}
