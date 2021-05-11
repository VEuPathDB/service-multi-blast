package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import io.vulpine.lib.query.util.basic.BasicPreparedReadQuery;
import mb.lib.model.HashID;
import mb.lib.query.model.BlastRow;

public class SelectBlastJob
{
  private static final String Query = """
    SELECT
      *
    FROM
      userlogins5.multiblast_jobs
    WHERE
      job_digest = ?
    """;

  private final Connection con;
  private final HashID     jobID;

  public SelectBlastJob(Connection con, HashID jobID) {
    this.con   = con;
    this.jobID = jobID;
  }

  public Optional<BlastRow> run() throws Exception {
    return new BasicPreparedReadQuery<>(Query, con, this::parse, this::prep).execute().getValue();
  }

  void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
  }

  Optional<BlastRow> parse(ResultSet rs) throws Exception {
    if (!rs.next())
      return Optional.empty();

    return Optional.of(Util.parseBlastRow(rs));
  }
}
