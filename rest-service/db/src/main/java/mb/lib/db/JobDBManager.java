package mb.lib.db;

import java.sql.Connection;
import java.time.OffsetDateTime;
import java.util.*;

import mb.lib.db.delete.DeleteJobQuery;
import mb.lib.db.delete.DeleteOrphanJobsQuery;
import mb.lib.db.delete.DeleteStaleGuestsQuery;
import mb.lib.db.delete.DeleteUsersByJobQuery;
import mb.lib.db.insert.InsertJobLink;
import mb.lib.db.insert.InsertJobQuery;
import mb.lib.db.insert.InsertJobTargets;
import mb.lib.db.insert.InsertUserQuery;
import mb.lib.db.model.*;
import mb.lib.db.select.*;
import mb.lib.db.update.UpdateJobDeleteDateQuery;
import mb.lib.db.update.UpdateJobQueueIDQuery;
import mb.lib.db.update.UpdateJobRunDirectly;
import mb.lib.db.update.UpdateJobStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager
{
  private static final Logger log = LogManager.getLogger(JobDBManager.class);

  public static Map<String, List<JobLink>> getAllParentJobs(long userID) throws Exception {
    log.trace("::getAllParentJobs(userID={})", userID);

    return new SelectLinksByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  /**
   * Attempts to retrieve a job by it's hash.
   */
  public static Optional<FullJobRow> getJob(Connection con, byte[] jobID) throws Exception {
    log.trace("::getJob(con={}, jobID={})", con, jobID);

    return new SelectJob(jobID).execute(con);
  }

  public static Set<JobLink> getJobLinks(Connection con, byte[] parentHash) throws Exception {
    log.trace("::getJobLinks(con={}, parentHash={})", con, parentHash);

    return new SelectLinksByParent(con, parentHash).run();
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public static boolean userIsLinkedToJob(Connection con, long userID, byte[] jobHash)
  throws Exception {
    log.trace("::userIsLinkedToJob(con={}, userID={}, jobHash={})", con, userID, jobHash);

    return new SelectUserIsLinkedToJob(con, userID, jobHash).run();
  }

  public static void createJobLink(Connection con, byte[] jobHash, byte[] parentHash)
  throws Exception {
    log.trace("::createJobLink(con={}, jobHash={}, parentHash={})", con, jobHash, parentHash);

    new InsertJobLink(con, jobHash, parentHash).run();
  }

  public static void updateJobDeleteTimer(Connection con, byte[] jobID, OffsetDateTime end)
  throws Exception {
    log.trace("::updateJobDeleteTimer(con={}, jobID={}, end={})", con, jobID, end);

    new UpdateJobDeleteDateQuery(con, jobID, end).run();
  }

  public static void updateJobQueueID(Connection con, byte[] jobID, int queueID) throws Exception {
    log.trace("::updateJobQueueID(con={}, jobID={}, queueID={})", con, jobID, queueID);

    new UpdateJobQueueIDQuery(con, jobID, queueID).run();
  }

  public static void linkUserToJob(Connection con, UserRow user) throws Exception {
    log.trace("::linkUserToJob(con={}, user={})", con, user);

    new InsertUserQuery(con, user).run();
  }

  public static void registerJob(
    Connection      con,
    FullJobRow      job,
    UserRow         user,
    List<JobTarget> targets
  ) throws Exception {
    log.trace("::registerJob(con={}, job={}, user={}, targets={})", con, job, user, targets);

    new InsertJobQuery(con, job).run();
    new InsertUserQuery(con, user).run();
    new InsertJobTargets(con, targets).run();
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
    log.trace("::deleteJob(jobID={})", jobID);

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
    log.trace("::getUserJob(jobID={}, userID={})", jobID, userID);

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
    log.trace("::getParentJobs(childHash={}, userID={})", childHash, userID);

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

  public static void updateLinkIsPrimary(Connection con, long userID, byte[] jobHash)
  throws Exception {
    log.trace("::updateLinkIsPrimary(con={}, userID={}, jobHash={})", con, userID, jobHash);

    new UpdateJobRunDirectly(con, true, userID, jobHash).run();
  }

  public static List<JobTarget> getJobTargetsFor(byte[] jobID) throws Exception {
    log.trace("::getJobTargetsFor(jobID={})", jobID);

    return new SelectTargetsByJob(DbManager.userDatabase().getDataSource(), jobID).run();
  }

  public static Map<String, List<JobTarget>> getJobTargetsFor(long userID) throws Exception {
    log.trace("::getJobTargetsFor(userID={})", userID);

    return new SelectTargetsByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  public static void updateJobStatus(Connection con, byte[] jobID, DBJobStatus status)
  throws Exception {
    log.trace("::updateJobStatus(con={}, jobID={}, status={})", con, jobID, status);

    new UpdateJobStatus(con, jobID, status).run();
  }
}
