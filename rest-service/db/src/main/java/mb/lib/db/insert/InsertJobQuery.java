package mb.lib.db.insert;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import mb.lib.db.model.JobRow;

public class InsertJobQuery
{
  private final Connection connection;
  private final JobRow job;

  public InsertJobQuery(Connection connection, JobRow job) {
    this.connection = connection;
    this.job        = job;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Insert.Job, connection, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setBytes(1, job.getJobHash());
    ps.setClob(2, new StringReader(job.getConfig()));
    ps.setObject(3, job.getCreatedOn(), Types.TIMESTAMP_WITH_TIMEZONE);
  }

  public static void execute(Connection con, JobRow job) throws Exception {
    new InsertJobQuery(con, job).run();
  }
}
