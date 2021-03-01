package mb.lib.db.insert;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.db.constants.SQL;

public class InsertJobLink
{
  private final Connection con;
  private final byte[]     jobHash;
  private final byte[] parentHash;

  public InsertJobLink(Connection con, byte[] jobHash, byte[] parentHash) {
    this.con        = con;
    this.jobHash    = jobHash;
    this.parentHash = parentHash;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(SQL.Insert.Link, con, this::prepare).execute();
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobHash);
    ps.setBytes(2, parentHash);
    ps.setBytes(3, parentHash);
  }
}
