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

import static mb.lib.db.constants.Column.MultiBlastJobToJobs.JobDigest;
import static mb.lib.db.constants.Column.MultiBlastJobToJobs.ParentDigest;

public class SelectLinksByUser
{
  private final Connection con;

  private final long userID;

  private final Map<String, List<JobLink>> links;

  public SelectLinksByUser(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
    this.links  = new HashMap<>();
  }

  public Map<String, List<JobLink>> run() throws Exception {
    try (
      var ps = con.prepareStatement(SQL.Select.MultiBlastJobToJobs.ByUserID)
    ) {
      ps.setLong(1, userID);

      try (var rs = ps.executeQuery()) {
        while (rs.next()) {
          var link = new JobLinkImpl(
            rs.getBytes(JobDigest),
            rs.getBytes(ParentDigest),
            rs.getInt(Column.MultiBlastJobToJobs.Position)
          );

          links.computeIfAbsent(bytesToHex(link.jobHash()), x -> new ArrayList<>()).add(link);
        }
      }
    }

    return links;
  }

  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  private static String bytesToHex(byte[] bytes) {
    char[] hexChars = new char[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }
}
