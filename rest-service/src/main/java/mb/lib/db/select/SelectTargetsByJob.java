package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.model.JobTarget;
import mb.lib.db.model.impl.JobTargetImpl;
import mb.lib.model.HashID;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobToTargets.ByJobID;

public class SelectTargetsByJob
{
  private final HashID     jobID;
  private final Connection con;

  public SelectTargetsByJob(Connection con, HashID jobID) {
    this.jobID = jobID;
    this.con   = con;
  }

  public List<JobTarget> run() throws Exception {
    return new BasicPreparedListReadQuery<>(ByJobID, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  JobTarget parse(ResultSet rs) throws Exception {
    return new JobTargetImpl(
      jobID,
      rs.getString(Column.MultiBlastJobToTargets.Organism),
      rs.getString(Column.MultiBlastJobToTargets.TargetFile)
    );
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, jobID.bytes());
  }
}
