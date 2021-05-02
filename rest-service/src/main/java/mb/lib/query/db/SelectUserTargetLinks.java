package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.BlastTargetLink;

public class SelectUserTargetLinks
{
  private static final String Query = """
    SELECT
      a.*
    FROM
      userlogins5.multiblast_job_to_targets a
      INNER JOIN userlogins5.multiblast_users mu
        ON a.job_digest = mu.job_digest
    WHERE
      mu.user_id = ?
    """;

  private final Connection con;
  private final long userID;

  public SelectUserTargetLinks(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
  }

  public Map<HashID, List<BlastTargetLink>> run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, this::parse, this::prep).execute().getValue();
  }

  private Map<HashID, List<BlastTargetLink>> parse(ResultSet rs) throws Exception {
    var out = new HashMap<HashID, List<BlastTargetLink>>();

    while (rs.next()) {
      var tmp = Util.parseTargetLink(rs);

      out.computeIfAbsent(tmp.getJobID(), k -> new ArrayList<>())
        .add(tmp);
    }

    return out;
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
  }
}
