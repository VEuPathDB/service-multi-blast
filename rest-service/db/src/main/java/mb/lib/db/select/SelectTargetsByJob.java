package mb.lib.db.select;

import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobTarget;
import mb.lib.db.model.impl.JobTargetImpl;

public class SelectTargetsByJob
{
  private final byte[] jobID;
  private final DataSource ds;

  public SelectTargetsByJob(DataSource ds, byte[] jobID) {
    this.jobID = jobID;
    this.ds    = ds;
  }

  public List<JobTarget> run() throws Exception {
    return new BasicPreparedListReadQuery<>(
      SQL.Select.MultiBlastJobToTargets.ByJobID,
      ds::getConnection,
      this::parse,
      ps -> ps.setBytes(1, jobID)
    ).execute().getValue();
  }

  JobTarget parse(ResultSet rs) throws Exception {
    return new JobTargetImpl(
      jobID,
      rs.getString(Column.MultiBlastJobToTargets.Organism),
      rs.getString(Column.MultiBlastJobToTargets.TargetFile)
    );
  }
}
