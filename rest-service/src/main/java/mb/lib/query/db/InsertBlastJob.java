package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.OffsetDateTime;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.query.model.BlastRow;
import mb.lib.util.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InsertBlastJob
{
  private static final Logger Log = LogManager.getLogger(InsertBlastJob.class);

  private static final String Query = """
    INSERT INTO
      userlogins5.multiblast_jobs (
        job_digest  -- 1
      , job_config
      , query
      , queue_id
      , project_id  -- 5
      , status
      , created_on
      , delete_on
      )
    VALUES
      (?, ?, ?, ?, ?, ?, ?, ?)
    """;

  private final Connection con;
  private final BlastRow row;

  public InsertBlastJob(Connection con, BlastRow row) {
    Log.trace("::new(con={}, row={})", con, row);
    this.con = con;
    this.row = row;
  }

  public void run() throws Exception {
    Log.trace("#run()");
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  private void prep(PreparedStatement ps) throws Exception {
    Log.trace("#prep(ps={})", ps);
    var time = OffsetDateTime.now();
    var json = JSON.stringify(row.getConfig());

    Log.debug(row.getConfig().getClass());
    Log.debug("Inserting job config: {}", json);

    ps.setBytes(1, row.getJobID().bytes());
    ps.setString(2, json);
    ps.setString(3, row.getQuery());
    ps.setInt(4, row.getQueueID());
    ps.setString(5, row.getProjectID());
    ps.setString(6, row.getStatus().getValue());
    ps.setObject(7, time);
    ps.setObject(8, time.plusDays(30));
  }
}
