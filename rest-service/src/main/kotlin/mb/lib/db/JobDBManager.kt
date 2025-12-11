package mb.lib.db

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

  @Override
  override fun close() = connection.close()
}
