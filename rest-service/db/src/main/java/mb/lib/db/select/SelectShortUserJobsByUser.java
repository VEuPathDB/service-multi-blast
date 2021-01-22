package mb.lib.db.select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobRowFactory;
import mb.lib.db.model.ShortUserJobRow;

public class SelectShortUserJobsByUser
{
  private DataSource ds;
  private final long userID;

  public SelectShortUserJobsByUser(DataSource ds, long userID) {
    this.ds     = ds;
    this.userID = userID;
  }

  public Collection<ShortUserJobRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(
      SQL.Select.MultiBlastJobs.ShortUserByUserID,
      ds::getConnection,
      this::parse,
      this::prep
    ).execute().getValue();
  }

  void prep(PreparedStatement stmt) throws Exception {
    stmt.setLong(1, this.userID);
  }

  ShortUserJobRow parse(ResultSet rs) throws Exception {
    return JobRowFactory.newShortUserJobRow(
      rs.getBytes(Column.MultiBlastJobs.JobDigest),
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getLong(Column.MultiBlastUsers.UserId),
      rs.getString(Column.MultiBlastUsers.Description)
    );
  }
}
