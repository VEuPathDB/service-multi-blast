package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.OffsetDateTime;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;

/**
 * Query for updating a job's expiration date.
 */
public class UpdateJobDeleteDateQuery
{
  private final Connection     con;
  private final byte[]         jobID;
  private final OffsetDateTime deleteOn;

  public UpdateJobDeleteDateQuery(Connection con, byte[] jobID, OffsetDateTime deleteOn) {
    this.con      = con;
    this.jobID    = jobID;
    this.deleteOn = deleteOn;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(SQL.Update.MultiBlastJobs.DeleteDate, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setObject(1, deleteOn, Types.TIMESTAMP_WITH_TIMEZONE);
    ps.setBytes(2, jobID);
  }
}
