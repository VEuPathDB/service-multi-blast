package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Optional;

import io.vulpine.lib.query.util.RowParser;
import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;

public class SelectBlastQuery
{
  private static final String Query = """
    SELECT
      query
    FROM
      userlogins5.multiblast_jobs
    WHERE
      job_digest = ?
    """;

  private final Connection con;
  private final HashID     jobID;

  public SelectBlastQuery(Connection con, HashID jobID) {
    this.con   = con;
    this.jobID = jobID;
  }

  public Optional<String> run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, RowParser::optionalString, this::prep)
      .execute()
      .getValue();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
  }
}
