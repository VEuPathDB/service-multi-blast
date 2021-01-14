package mb.lib.db.select;

import java.time.OffsetDateTime;
import java.util.Collection;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.StatementPreparer;
import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;

public class SelectJobsByUserQuery
{
  private final DataSource ds;
  private final long userId;

  public SelectJobsByUserQuery(DataSource ds, long userId) {
    this.ds     = ds;
    this.userId = userId;
  }

  public Collection<FullJobRow> run() throws Exception {
    return new BasicPreparedListReadQuery<FullJobRow>(
      SQL.Select.MultiBlastJobs.ByUser,
      ds::getConnection,
      rs -> new FullJobRowImpl(
        rs.getBytes(Column.MultiBlastJobs.JobDigest),
        rs.getString(Column.MultiBlastJobs.JobConfig),
        rs.getInt(Column.MultiBlastJobs.QueueID),
        rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
        rs.getString(Column.MultiBlastUsers.Description),
        rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class)
      ),
      StatementPreparer.singleLong(userId)
    ).execute().getValue();
  }

  public static Collection<FullJobRow> execute(DataSource ds, long userId) throws Exception {
    return new SelectJobsByUserQuery(ds, userId).run();
  }
}
