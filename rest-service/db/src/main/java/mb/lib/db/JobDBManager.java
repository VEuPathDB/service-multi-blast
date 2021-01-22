package mb.lib.db;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Optional;

import mb.lib.db.delete.DeleteJobQuery;
import mb.lib.db.delete.DeleteUsersByJobQuery;
import mb.lib.db.insert.InsertJobQuery;
import mb.lib.db.insert.InsertUserQuery;
import mb.lib.db.model.FullJobRow;
import mb.lib.db.model.FullUserJobRow;
import mb.lib.db.model.ShortUserJobRow;
import mb.lib.db.model.UserRow;
import mb.lib.db.select.SelectFullUserJob;
import mb.lib.db.select.SelectJob;
import mb.lib.db.select.SelectShortUserJobsByUser;
import mb.lib.db.select.SelectStaleJobsQuery;
import mb.lib.db.update.UpdateJobDeleteDateQuery;
import mb.lib.db.update.UpdateJobQueueIDQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager
{
  private static final Logger log = LogManager.getLogger(JobDBManager.class);

  public static boolean jobExists(byte[] jobID) throws Exception {
    log.trace("JobDBManager#jobExists(byte[])");
    return new SelectJob(jobID).execute(DbManager.userDatabase().getDataSource()::getConnection).
      isPresent();
  }

  public static Optional<FullJobRow> getJob(byte[] jobID) throws Exception {
    log.trace("JobDBManager#getJob(byte[])");
    return new SelectJob(jobID).execute(DbManager.userDatabase().getDataSource()::getConnection);
  }

  public static void updateJobDeleteTimer(byte[] jobID, OffsetDateTime end) throws Exception {
    log.trace("JobDBManager#updateJobDeleteTimer(byte[], OffsetDateTime)");
    new UpdateJobDeleteDateQuery(jobID, end).run();
  }

  public static void updateJobQueueID(byte[] jobID, int queueID) throws Exception {
    log.trace("JobDBManager#updateJobQueueID(byte[], int)");
    new UpdateJobQueueIDQuery(jobID, queueID).run();
  }

  public static void linkUserToJob(UserRow user) throws Exception {
    log.trace("JobDBManager#linkUserToJob(UserRow)");
    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertUserQuery(con, user).run();
    }
  }

  public static void registerJob(FullJobRow job, UserRow user) throws Exception {
    log.trace("JobDBManager#registerJob(FullJobRow, UserRow)");
    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertJobQuery(con, job).run();
      new InsertUserQuery(con, user).run();
    }
  }

  public static Collection<FullJobRow> getStaleJobs(OffsetDateTime asOf) throws Exception {
    log.trace("JobDBManager#getStaleJobs(OffsetDateTime)");
    return new SelectStaleJobsQuery(asOf).run();
  }

  /**
   * Deletes a job and all associated users from the WDK user database.
   *
   * @param jobID ID of the job to delete.
   */
  public static void deleteJob(byte[] jobID) throws Exception {
    log.trace("JobDBManager#deleteJob(byte[])");
    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new DeleteUsersByJobQuery(jobID, con).run();
      new DeleteJobQuery(jobID, con).run();
    }
  }

  /**
   * Attempts to retrieve a job record with user metadata for the given
   * combination of user and job ids.
   *
   * @param jobID  Job ID for the lookup.
   * @param userID User ID for the lookup.
   *
   * @return An option containing a full record of the intersection between the
   * user metadata and the job list including all job and user data. If no such
   * intersection could be found, the option will be empty.
   */
  public static Optional<FullUserJobRow> getUserJob(byte[] jobID, long userID) throws Exception {
    log.trace("JobDBManager#getUserJob(byte[], long)");
    return new SelectFullUserJob(DbManager.userDatabase().getDataSource(), jobID, userID).run();
  }

  /**
   * Retrieves a collection of the jobs associated with the given user ID
   * including additional user specific metadata.
   *
   * @param userID ID of the user whose jobs should be retrieved.
   *
   * @return A collection of jobs associated with the given user ID.
   */
  public static Collection<ShortUserJobRow> getUserJobs(long userID) throws Exception {
    log.trace("JobDBManager#getUserJobs(long)");
    return new SelectShortUserJobsByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }
}
