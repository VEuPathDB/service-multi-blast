package mb.lib.db.insert;

import java.sql.Connection;
import java.util.List;

import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobTarget;

public class InsertJobTargets
{
  private final Connection con;
  private final List<JobTarget> jt;

  public InsertJobTargets(Connection con, List<JobTarget> jt) {
    this.con = con;
    this.jt  = jt;
  }

  public void run() throws Exception {
    try (var stmt = con.prepareStatement(SQL.Insert.Target)) {
      for (var jt : this.jt) {
        stmt.setBytes(1, jt.jobHash());
        stmt.setString(2, jt.organism());
        stmt.setString(3, jt.targetFile());
        stmt.addBatch();
      }

      stmt.executeBatch();
    }
  }
}
