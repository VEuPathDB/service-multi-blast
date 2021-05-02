package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.query.model.JobLinkCollection;

public class SelectUserJobLinks
{
  private static final String Query = """
    SELECT
      a.*
    FROM
      userlogins5.multiblast_job_to_jobs a
      INNER JOIN userlogins5.multiblast_users b
        ON a.job_digest = b.job_digest
    WHERE
      user_id = ?
    """;

  private final Connection        con;
  private final long              userID;

  public SelectUserJobLinks(Connection con, long userID) {
    this.con    = con;
    this.userID = userID;
  }

  public JobLinkCollection run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, this::parse, this::prep).
      execute().
      getValue();
  }

  private JobLinkCollection parse(ResultSet rs) throws Exception {
    var output = new JobLinkCollection();

    while (rs.next()) {
      var link = Util.parseJobLink(rs);

      output.byParentID
        .computeIfAbsent(link.getParentJobID(), k -> new ArrayList<>())
        .add(link);
      output.byChildID
        .computeIfAbsent(link.getChildJobID(), k -> new ArrayList<>())
        .add(link);
    }

    return output;
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
  }
}
