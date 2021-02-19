package mb.lib.db;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import mb.lib.db.delete.DeleteJobQuery;
import mb.lib.db.delete.DeleteOrphanJobsQuery;
import mb.lib.db.delete.DeleteStaleGuestsQuery;
import mb.lib.db.delete.DeleteUsersByJobQuery;
import mb.lib.db.insert.InsertJobLink;
import mb.lib.db.insert.InsertJobQuery;
import mb.lib.db.insert.InsertUserQuery;
import mb.lib.db.model.*;
import mb.lib.db.select.*;
import mb.lib.db.update.UpdateJobDeleteDateQuery;
import mb.lib.db.update.UpdateJobQueueIDQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager
{
  private static final Logger log = LogManager.getLogger(JobDBManager.class);

  /**
   * Attempts to retrieve a job by it's hash.
   */
  public static Optional<FullJobRow> getJob(byte[] jobID) throws Exception {
    log.trace("::getJob(jobID={})", h2s(jobID));

    return new SelectJob(jobID).execute(DbManager.userDatabase().getDataSource()::getConnection);
  }

  public static Set<JobLink> getJobLinks(byte[] parentHash) throws Exception {
    log.trace("::getJobLinks(parentHash={})", h2s(parentHash));

    return new SelectLinksByParent(DbManager.userDatabase().getDataSource(), parentHash).run();
  }

  public static boolean userIsLinkedToJob(long userID, byte[] jobHash) throws Exception {
    log.trace("::userIsLinkedToJob(userID={}, jobHash={})", userID, h2s(jobHash));

    return new SelectUserIsLinkedToJob(DbManager.userDatabase().getDataSource(), userID, jobHash)
      .run();
  }

  public static void createJobLink(byte[] jobHash, byte[] parentHash) throws Exception {
    log.trace("::createJobLink(jobHash={}, parentHash={})", h2s(jobHash), h2s(parentHash));

    new InsertJobLink(DbManager.userDatabase().getDataSource(), jobHash, parentHash).run();
  }

  public static void updateJobDeleteTimer(byte[] jobID, OffsetDateTime end) throws Exception {
    log.trace("::updateJobDeleteTimer(jobID={}, end={})", h2s(jobID), end);

    new UpdateJobDeleteDateQuery(jobID, end).run();
  }

  public static void updateJobQueueID(byte[] jobID, int queueID) throws Exception {
    log.trace("::updateJobQueueID(jobID={}, queueID={})", h2s(jobID), queueID);

    new UpdateJobQueueIDQuery(jobID, queueID).run();
  }

  public static void linkUserToJob(UserRow user) throws Exception {
    log.trace("::linkUserToJob(user={})", user);

    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertUserQuery(con, user).run();
    }
  }

  public static void registerJob(FullJobRow job, UserRow user) throws Exception {
    log.trace("::registerJob(job={}, user={})", job, user);

    log.warn(job.query());

    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertJobQuery(con, job).run();
      new InsertUserQuery(con, user).run();
    }
  }

  public static Collection<FullJobRow> getStaleJobs(OffsetDateTime asOf) throws Exception {
    log.trace("::getStaleJobs(asOf={})", asOf);
    return new SelectStaleJobsQuery(asOf).run();
  }

  /**
   * Deletes a job and all associated users from the WDK user database.
   *
   * @param jobID ID of the job to delete.
   */
  public static void deleteJob(byte[] jobID) throws Exception {
    log.trace("::deleteJob(jobID={})", h2s(jobID));
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
    log.trace("::getUserJob(jobID={}, userID={})", h2s(jobID), userID);
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
    log.trace("::getUserJobs(userID={})", userID);
    return new SelectShortUserJobsByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  public static List<JobLink> getParentJobs(byte[] childHash, long userID) throws Exception {
    log.trace("::getParentJob(childHash={}, userID={})", h2s(childHash), userID);
    return new SelectParentJobs(DbManager.userDatabase().getDataSource(), childHash, userID).run();
  }

  public static void deleteStaleGuests() throws Exception {
    log.trace("::deleteStaleGuests()");
    new DeleteStaleGuestsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  public static void deleteOrphanJobs() throws Exception {
    log.trace("::deleteOrphanJobs()");
    new DeleteOrphanJobsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
  public static String h2s(byte[] hash) {
    char[] hexChars = new char[hash.length * 2];
    for (int j = 0; j < hash.length; j++) {
      int v = hash[j] & 0xFF;
      hexChars[j * 2]     = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars);
  }
}
