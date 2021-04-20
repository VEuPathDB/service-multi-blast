package mb.lib.db.select;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicStatementListReadQuery;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobs.Orphaned;

public class SelectOrphanedJobs
{
  private final Connection con;

  public SelectOrphanedJobs(Connection con) {
    this.con = con;
  }

  public List<HashID> run() throws Exception {
    return new BasicStatementListReadQuery<>(Orphaned, con, this::parse).execute().getValue();
  }

  private HashID parse(ResultSet rs) throws Exception {
    return new HashID(rs.getBytes(1));
  }
}
