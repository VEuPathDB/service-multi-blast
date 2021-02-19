package mb.lib.db.select;

import java.sql.ResultSet;
import java.util.Set;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedSetReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.JobLinkImpl;

public class SelectLinksByParent
{
  private final DataSource ds;
  private final byte[] parentHash;

  public SelectLinksByParent(DataSource ds, byte[] parentHash) {
    this.ds         = ds;
    this.parentHash = parentHash;
  }

  public Set<JobLink> run() throws Exception {
    return new BasicPreparedSetReadQuery<>(
      SQL.Select.MultiBlastJobToJobs.ByParent,
      ds::getConnection,
      this::rowToJobLink,
      ps -> ps.setBytes(1, parentHash)
    ).execute().getValue();
  }

  JobLink rowToJobLink(ResultSet rs) throws Exception {
    return new JobLinkImpl(
      rs.getBytes(Column.MultiBlastJobToJobs.JobDigest),
      rs.getBytes(Column.MultiBlastJobToJobs.ParentDigest),
      rs.getInt(Column.MultiBlastJobToJobs.Position)
    );
  }
}
