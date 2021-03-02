package mb.lib.db.select;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobTarget;
import mb.lib.db.model.impl.JobTargetImpl;

import static mb.lib.db.constants.Column.MultiBlastJobToTargets.*;

public class SelectTargetsByUser
{
  private final long userID;

  private final Connection con;

  private final Map<String, List<JobTarget>> out;

  public SelectTargetsByUser(Connection con, long userID) {
    this.userID = userID;
    this.con    = con;
    this.out    = new HashMap<>();
  }

  public Map<String, List<JobTarget>> run() throws Exception {
    try (
      var ps  = con.prepareStatement(SQL.Select.MultiBlastJobToTargets.ByUserID)
    ) {
      ps.setLong(1, userID);

      try (var rs = ps.executeQuery()) {
        while (rs.next()) {
          var tgt = new JobTargetImpl(
            rs.getBytes(JobDigest),
            rs.getString(Organism),
            rs.getString(TargetFile)
          );

          out.computeIfAbsent(tgt.printID(), x -> new ArrayList<>()).add(tgt);
        }
      }
    }

    return out;
  }
}
