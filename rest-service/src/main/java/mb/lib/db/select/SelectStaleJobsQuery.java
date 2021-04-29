package mb.lib.db.select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.Collection;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class SelectStaleJobsQuery
{
  private final OffsetDateTime asOf;

  public SelectStaleJobsQuery(OffsetDateTime asOf) {
    this.asOf = asOf;
  }

  public Collection<FullJobRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(
      SQL.Select.MultiBlastJobs.Stale,
      DbManager.userDatabase().getDataSource()::getConnection,
      this::parse,
      this::prepare
    ).execute().getValue();
  }

  FullJobRow parse(ResultSet rs) throws Exception {
    return new FullJobRowImpl(
      new HashID(rs.getBytes(Column.MultiBlastJobs.JobDigest)),
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.JobConfig),
      Util.queryToFile(rs),
      rs.getString(Column.MultiBlastJobs.ProjectID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status))
    );
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setObject(1, asOf, Types.TIMESTAMP_WITH_TIMEZONE);
  }
}
