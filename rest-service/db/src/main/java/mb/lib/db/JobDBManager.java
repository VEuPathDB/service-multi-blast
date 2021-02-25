package mb.lib.db;

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
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager
{
  public static Map<String, List<JobLink>> getAllParentJobs(long userID) throws Exception {
    return new SelectLinksByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  /**
   * Attempts to retrieve a job by it's hash.
   */
  public static Optional<FullJobRow> getJob(byte[] jobID) throws Exception {
    return new SelectJob(jobID).execute(DbManager.userDatabase().getDataSource()::getConnection);
  }

  public static Set<JobLink> getJobLinks(byte[] parentHash) throws Exception {
    return new SelectLinksByParent(DbManager.userDatabase().getDataSource(), parentHash).run();
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public static boolean userIsLinkedToJob(long userID, byte[] jobHash) throws Exception {
    return new SelectUserIsLinkedToJob(DbManager.userDatabase().getDataSource(), userID, jobHash)
      .run();
  }

  public static void createJobLink(byte[] jobHash, byte[] parentHash) throws Exception {
    new InsertJobLink(DbManager.userDatabase().getDataSource(), jobHash, parentHash).run();
  }

  public static void updateJobDeleteTimer(byte[] jobID, OffsetDateTime end) throws Exception {
    new UpdateJobDeleteDateQuery(jobID, end).run();
  }

  public static void updateJobQueueID(byte[] jobID, int queueID) throws Exception {
    new UpdateJobQueueIDQuery(jobID, queueID).run();
  }

  public static void linkUserToJob(UserRow user) throws Exception {
    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertUserQuery(con, user).run();
    }
  }

  public static void registerJob(
    FullJobRow      job,
    UserRow         user,
    List<JobTarget> targets
  ) throws Exception {
    try (var con = DbManager.userDatabase().getDataSource().getConnection()) {
      new InsertJobQuery(con, job).run();
      new InsertUserQuery(con, user).run();
      new InsertJobTargets(con, targets).run();
    }
  }

  public static Collection<FullJobRow> getStaleJobs(OffsetDateTime asOf) throws Exception {
    return new SelectStaleJobsQuery(asOf).run();
  }

  /**
   * Deletes a job and all associated users from the WDK user database.
   *
   * @param jobID ID of the job to delete.
   */
  public static void deleteJob(byte[] jobID) throws Exception {
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
    return new SelectShortUserJobsByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  public static List<JobLink> getParentJobs(byte[] childHash, long userID) throws Exception {
    return new SelectParentJobs(DbManager.userDatabase().getDataSource(), childHash, userID).run();
  }

  public static void deleteStaleGuests() throws Exception {
    new DeleteStaleGuestsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  public static void deleteOrphanJobs() throws Exception {
    new DeleteOrphanJobsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  public static void updateLinkIsPrimary(long userID, byte[] jobHash) throws Exception {
    new UpdateJobRunDirectly(DbManager.userDatabase().getDataSource(), true, userID, jobHash).run();
  }

  public static List<JobTarget> getJobTargetsFor(byte[] jobID) throws Exception {
    return new SelectTargetsByJob(DbManager.userDatabase().getDataSource(), jobID).run();
  }

  public static Map<String, List<JobTarget>> getJobTargetsFor(long userID) throws Exception {
    return new SelectTargetsByUser(DbManager.userDatabase().getDataSource(), userID).run();
  }

  public static void updateJobStatus(byte[] jobID, DBJobStatus status) throws Exception {
    new UpdateJobStatus(jobID, status).run();
  }
}
