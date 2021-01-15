package mb.lib.db.update;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.OffsetDateTime;

import io.vulpine.lib.query.util.basic.BasicPreparedWriteQuery;
import mb.lib.db.constants.SQL;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class UpdateJobDeleteDateQuery
{
  private final byte[]     jobID;
  private final OffsetDateTime deleteOn;

  public UpdateJobDeleteDateQuery(byte[] jobID, OffsetDateTime deleteOn) {
    this.jobID = jobID;
    this.deleteOn = deleteOn;
  }

  public void run() throws Exception {
    new BasicPreparedWriteQuery(
      SQL.Update.MultiBlastJobs.DeleteDate,
      DbManager.userDatabase().getDataSource()::getConnection,
      this::prepare
    ).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    ps.setObject(1, deleteOn, Types.TIMESTAMP_WITH_TIMEZONE);
    ps.setBytes(2, jobID);
  }
}
