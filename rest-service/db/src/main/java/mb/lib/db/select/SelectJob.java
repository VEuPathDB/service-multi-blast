package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.OffsetDateTime;
import java.util.Optional;

import io.vulpine.lib.jcfi.CheckedSupplier;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;
import oracle.jdbc.OracleType;
import oracle.sql.RAW;

public class SelectJob
{
  private final byte[] hash;

  public SelectJob(byte[] hash) {
    this.hash = hash;
  }

  public Optional<FullJobRow> execute(CheckedSupplier<Connection> provider) throws Exception {
    return new BasicPreparedReadQuery<>(
      SQL.Select.MultiBlastJobs.ById,
      provider::get,
      this::parse,
      this::prep
    ).execute().getValue();
  }

  void prep(PreparedStatement stmt) throws Exception {
    stmt.setObject(1, new RAW(this.hash), OracleType.RAW);
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
      Util.queryToFile(rs)
    ));
  }

}
