package mb.lib.db.select;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.util.Collection;

import io.vulpine.lib.query.util.basic.BasicPreparedListReadQuery;
import mb.lib.db.constants.Column;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.DBJobStatus;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.impl.FullJobRowImpl;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

import static mb.lib.db.constants.SQL.Select.MultiBlastJobs.ByParent;

public class SelectChildJobsQuery
{
  private final byte[]     parentID;
  private final Connection con;

  public SelectChildJobsQuery(Connection con, byte[] parentID) {
    this.con      = con;
    this.parentID = parentID;
  }

  public Collection<FullJobRow> run() throws Exception {
    return new BasicPreparedListReadQuery<>(ByParent, con, this::parse, this::prepare)
      .execute()
      .getValue();
  }

  FullJobRow parse(ResultSet rs) throws Exception {
    return new FullJobRowImpl(
      rs.getBytes(Column.MultiBlastJobs.JobDigest),
      rs.getInt(Column.MultiBlastJobs.QueueID),
      rs.getObject(Column.MultiBlastJobs.CreatedOn, OffsetDateTime.class),
      rs.getObject(Column.MultiBlastJobs.DeleteOn, OffsetDateTime.class),
      rs.getString(Column.MultiBlastJobs.JobConfig),
      Util.queryToFile(rs),
      rs.getString(Column.MultiBlastJobs.ProjectID),
      DBJobStatus.fromString(rs.getString(Column.MultiBlastJobs.Status))
    );
  }

  void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, parentID);
  }
}
