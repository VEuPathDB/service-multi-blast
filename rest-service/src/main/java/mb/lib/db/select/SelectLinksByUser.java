package mb.lib.db.select;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.JobLinkImpl;
import mb.lib.model.HashID;

import static mb.lib.db.constants.Column.MultiBlastJobToJobs.JobDigest;
import static mb.lib.db.constants.Column.MultiBlastJobToJobs.ParentDigest;

public class SelectLinksByUser
{
  private final Connection con;

  private final long userID;

  private final Map<HashID, List<JobLink>> links;

  public SelectLinksByUser(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
    this.links  = new HashMap<>();
  }

  public Map<HashID, List<JobLink>> run() throws Exception {
    try (
      var ps = con.prepareStatement(SQL.Select.MultiBlastJobToJobs.ByUserID)
    ) {
      ps.setLong(1, userID);

      try (var rs = ps.executeQuery()) {
        while (rs.next()) {
          var link = new JobLinkImpl(
            new HashID(rs.getBytes(JobDigest)),
            new HashID(rs.getBytes(ParentDigest)),
            rs.getInt(Column.MultiBlastJobToJobs.Position)
          );

          links.computeIfAbsent(link.childID(), x -> new ArrayList<>()).add(link);
        }
      }
    }

    return links;
  }
}
