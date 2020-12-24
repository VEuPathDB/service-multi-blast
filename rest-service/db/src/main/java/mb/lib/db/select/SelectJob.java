package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.jcfi.CheckedSupplier;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.db.model.JobRow;
import oracle.jdbc.OracleType;
import oracle.sql.RAW;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;

public class SelectJob
{
  private final byte[] hash;

  public SelectJob(byte[] hash) {
    this.hash = hash;
  }

  public Optional<JobRow> execute(CheckedSupplier<Connection> provider) throws Exception {
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

  Optional<JobRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(new JobRow(this.hash, rs.getString(Column.MultiBlastJobs.JobConfig)));
  }

}
