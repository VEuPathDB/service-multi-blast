package mb.lib.db.insert;

import java.io.FileReader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.FullJobRow;

public class InsertJobQuery
{
  private final Connection connection;
  private final FullJobRow job;

  public InsertJobQuery(Connection connection, FullJobRow job) {
    this.connection = connection;
    this.job        = job;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Insert.Job, connection, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, job.jobID());
    ps.setClob(2, new StringReader(job.config()));
    ps.setClob(3, new FileReader(job.query()));
    ps.setInt(4, job.queueID());
    ps.setString(5, job.projectID());
    ps.setString(6, job.status().value);
    ps.setObject(7, job.createdOn(), Types.TIMESTAMP_WITH_TIMEZONE);
    ps.setObject(8, job.deleteOn(), Types.TIMESTAMP_WITH_TIMEZONE);
  }
}
