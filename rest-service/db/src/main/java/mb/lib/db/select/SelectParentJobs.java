package mb.lib.db.select;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.JobLinkImpl;

public class SelectParentJobs
{
  private final DataSource ds;
  private final long userID;
  private final byte[] jobHash;

  public SelectParentJobs(DataSource ds, byte[] jobHash, long userID) {
    this.ds      = ds;
    this.userID  = userID;
    this.jobHash = jobHash;
  }

  public List<JobLink> run() throws Exception {
    return new BasicPreparedListReadQuery<>(
      SQL.Select.MultiBlastJobToJobs.GetParent,
      ds::getConnection,
      this::parseRow,
      this::prepare
    ).execute().getValue();
  }

  JobLink parseRow(ResultSet rs) throws Exception {
    return new JobLinkImpl(jobHash, rs.getBytes(1), rs.getInt(2));
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
    ps.setBytes(2, jobHash);
  }

}
