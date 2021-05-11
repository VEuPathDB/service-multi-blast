package mb.lib.db;

import java.sql.Connection;

import mb.lib.db.select.SelectUserIsGuest;
import mb.lib.db.update.UpdateJobOwner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager implements AutoCloseable
{
  private static final Logger Log = LogManager.getLogger(JobDBManager.class);

  private final Connection connection;

  public JobDBManager() throws Exception {
    this.connection = DbManager.userDatabase().getDataSource().getConnection();
  }

  /**
   * Updates the owner field on all jobs owned by the user identified by
   * {@code oldUserID} to instead be owned by the user identified by
   * {@code newUserID}.
   *
   * @param oldUserID ID of the current job owner.
   * @param newUserID ID of the new job owner.
   */
  public void updateJobOwner(long oldUserID, long newUserID) throws Exception {
    Log.trace("#updateJobOwner(oldUserID={}, newUserID={})", oldUserID, newUserID);

    new UpdateJobOwner(this.connection, oldUserID, newUserID).run();
  }

  /**
   * Looks up whether the user identified by the given {@code userID} is a
   * guest.
   *
   * @param userID ID of the user to test.
   *
   * @return {@code true} if the user was located and is marked as a guest user.
   * {@code false} if the user was not found, or if the user was found but is
   * not marked as a guest.
   */
  public boolean userIsGuest(long userID) throws Exception {
    Log.trace("#userIsGuest(userID={})", userID);

    return new SelectUserIsGuest(connection, userID).run();
  }

  @Override
  public void close() throws Exception {
    this.connection.close();
  }
}
