package mb.lib.db

import mb.lib.db.select.SelectUserIsGuest
import mb.lib.db.update.UpdateJobOwner
import org.apache.logging.log4j.LogManager
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager

class JobDBManager : AutoCloseable {
  companion object {
    private val Log = LogManager.getLogger(JobDBManager::class.java)
  }

  private val connection = DbManager.userDatabase().dataSource.connection

  /**
   * Updates the owner field on all jobs owned by the user identified by
   * {@code oldUserID} to instead be owned by the user identified by
   * {@code newUserID}.
   *
   * @param oldUserID ID of the current job owner.
   * @param newUserID ID of the new job owner.
   */
  fun updateJobOwner(oldUserID: Long, newUserID: Long) {
    Log.trace("#updateJobOwner(oldUserID={}, newUserID={})", oldUserID, newUserID)

    UpdateJobOwner(this.connection, oldUserID, newUserID).run()
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
  fun userIsGuest(userID: Long): Boolean {
    Log.trace("#userIsGuest(userID={})", userID)

    return SelectUserIsGuest(connection, userID).run()
  }

  @Override
  override fun close() = connection.close()
}
