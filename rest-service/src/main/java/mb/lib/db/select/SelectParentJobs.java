package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.model.JobLink;
import mb.lib.db.model.impl.JobLinkImpl;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobToJobs.GetParent;

public class SelectParentJobs
{
  private final Connection con;
  private final long   userID;
  private final HashID jobHash;

  public SelectParentJobs(Connection con, HashID jobHash, long userID) {
    this.con     = con;
    this.userID  = userID;
    this.jobHash = jobHash;
  }

  public List<JobLink> run() throws Exception {
    return new BasicPreparedListReadQuery<>(GetParent, con, this::parseRow, this::prepare)
      .execute()
      .getValue();
  }

  JobLink parseRow(ResultSet rs) throws Exception {
    return new JobLinkImpl(jobHash, new HashID(rs.getBytes(1)), rs.getInt(2));
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setLong(1, userID);
    ps.setBytes(2, jobHash.bytes());
  }

}
