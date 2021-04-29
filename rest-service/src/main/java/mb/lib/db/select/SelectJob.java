package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobs.ById;

public class SelectJob
{
  private final HashID hash;

  public SelectJob(HashID hash) {
    this.hash = hash;
  }

  public Optional<FullJobRow> execute(Connection con) throws Exception {
    return new BasicPreparedReadQuery<>(ById, con, this::parse, this::prep).execute().getValue();
  }

  void prep(PreparedStatement stmt) throws Exception {
    stmt.setBytes(1, hash.bytes());
  }

  Optional<FullJobRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new FullJobRowImpl(
      this.hash,
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.JobConfig),
      Util.queryToFile(rs),
      rs.getString(Column.MultiBlastJobs.ProjectID),
      JobStatus.unsafeFromString(rs.getString(Column.MultiBlastJobs.Status))
    ));
  }

}
