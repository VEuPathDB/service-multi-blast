package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.BlastJobLink;

public class SelectChildBlastLinks
{
  private static final String Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_job_to_jobs
    WHERE
      parent_digest = ?
    ORDER BY
      position ASC
    """;

  private final Connection con;
  private final HashID     parentJobID;

  public SelectChildBlastLinks(Connection con, HashID parentJobID) {
    this.con         = con;
    this.parentJobID = parentJobID;
  }

  public List<BlastJobLink> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::parseJobLink, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, parentJobID.bytes());
  }
}
