package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.BlastJobLink;

public class SelectParentJobLinks
{
  private static final String Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_job_to_jobs
    WHERE
      job_digest = ?
    ORDER BY
      position ASC
    """;

  private final Connection con;
  private final HashID     childJobID;

  public SelectParentJobLinks(Connection con, HashID childJobID) {
    this.con        = con;
    this.childJobID = childJobID;
  }

  public List<BlastJobLink> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::parseJobLink, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, childJobID.bytes());
  }
}
