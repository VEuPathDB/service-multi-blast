package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;

import io.vulpine.lib.query.util.basic.BasicPreparedSetReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.JobLinkImpl;

public class SelectLinksByParent
{
  private final Connection con;
  private final byte[]     parentHash;

  public SelectLinksByParent(Connection con, byte[] parentHash) {
    this.con        = con;
    this.parentHash = parentHash;
  }

  public Set<JobLink> run() throws Exception {
    return new BasicPreparedSetReadQuery<>(
      SQL.Select.MultiBlastJobToJobs.ByParent,
      con,
      this::rowToJobLink,
      this::prepare
    ).execute().getValue();
  }

  JobLink rowToJobLink(ResultSet rs) throws Exception {
    return new JobLinkImpl(
      rs.getBytes(Column.MultiBlastJobToJobs.JobDigest),
      rs.getBytes(Column.MultiBlastJobToJobs.ParentDigest),
      rs.getInt(Column.MultiBlastJobToJobs.Position)
    );
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, parentHash);
  }
}
