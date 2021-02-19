package mb.lib.db.insert;

import java.sql.PreparedStatement;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.db.constants.SQL;

public class InsertJobLink
{
  private final DataSource ds;
  private final byte[] jobHash;
  private final byte[] parentHash;

  public InsertJobLink(DataSource ds, byte[] jobHash, byte[] parentHash) {
    this.ds         = ds;
    this.jobHash    = jobHash;
    this.parentHash = parentHash;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(
      SQL.Insert.Link,
      ds::getConnection,
      this::prepare
    ).execute();
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobHash);
    ps.setBytes(2, parentHash);
    ps.setBytes(3, parentHash);
  }
}
