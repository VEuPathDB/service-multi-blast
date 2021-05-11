package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.BlastTargetLink;

public class SelectTargetLinks
{
  private static final String Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_job_to_targets
    WHERE
      job_digest = ?
    """;

  private final Connection con;
  private final HashID     jobID;

  public SelectTargetLinks(Connection con, HashID jobID) {
    this.con   = con;
    this.jobID = jobID;
  }

  public List<BlastTargetLink> run() throws Exception {
    return new BasicPreparedListReadQuery<>(Query, con, Util::parseTargetLink, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
  }
}
