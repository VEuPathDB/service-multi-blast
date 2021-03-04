package mb.lib.db.update;

import java.sql.Connection;
import java.sql.PreparedStatement;

import io.vulpine.lib.query.util.basic.BasicPreparedVoidQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static mb.lib.db.constants.SQL.Update.MultiBlastUsers.Owner;

/**
 * Query for updating user-to-job links from one user ID to another.
 */
public class UpdateJobOwner
{
  private static final Logger log = LogManager.getLogger(UpdateJobOwner.class);

  private final Connection con;
  private final long       oldID;
  private final long       newID;

  public UpdateJobOwner(Connection con, long oldID, long newID) {
    log.trace("#new(con={}, oldID={}, newID={})", con, oldID, newID);

    this.con   = con;
    this.oldID = oldID;
    this.newID = newID;
  }

  public void run() throws Exception {
    log.trace("#run()");

    new BasicPreparedVoidQuery(Owner, con, this::prepare).execute();
  }

  private void prepare(PreparedStatement ps) throws Exception {
    log.trace("#prepare(ps={})", ps);

    ps.setLong(1, newID);
    ps.setLong(2, oldID);
  }
}
