package mb.lib.query.db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import mb.lib.query.model.UserBlastRow;

public class InsertUserLink
{
  private static final String Query = """
    INSERT INTO
      userlogins5.multiblast_users (
        job_digest
      , user_id
      , description
      , max_download_size
      , run_directly
      )
    VALUES
      (?, ?, ?, ?, ?)
    """;

  private final Connection   con;
  private final UserBlastRow row;

  public InsertUserLink(Connection con, UserBlastRow row) {
    this.con = con;
    this.row = row;
  }

  public void run() throws Exception {
    new BasicPreparedVoidQuery(Query, con, this::prep).execute();
  }

  private void prep(PreparedStatement ps) throws Exception {
    ps.setBytes(1, row.getJobID().bytes());
    ps.setLong(2, row.getUserID());
    ps.setString(3, row.getDescription());
    ps.setLong(4, row.getMaxDownloadSize());
    ps.setBoolean(5, row.isRunDirectly());
  }
}
