package mb.lib.db;

import java.sql.Connection;
import java.time.OffsetDateTime;
import java.util.*;

import mb.lib.db.delete.*;
import mb.lib.db.insert.InsertJobLink;
import mb.lib.db.insert.InsertJobQuery;
import mb.lib.db.insert.InsertJobTargets;
import mb.lib.db.insert.InsertUserQuery;
import mb.lib.db.model.*;
import mb.lib.db.select.*;
import mb.lib.db.update.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.container.jaxrs.utils.db.DbManager;

public class JobDBManager implements AutoCloseable
{
  private static final Logger log = LogManager.getLogger(JobDBManager.class);

  private final Connection connection;

  public JobDBManager() throws Exception {
    this.connection = DbManager.userDatabase().getDataSource().getConnection();
  }

  /**
   * Retrieves the user-to-job link for the user and job identified by the given
   * ID and hash.
   *
   * @param userID ID of the user for which the link should be retrieved.
   * @param jobID  Hash of the job for which the link should be retrieved.
   *
   * @return An option containing a user-to-job link.  If the user identified by
   * the given {@code userID} is not connected to any job with the hash
   * {@code jobID}, this option will be empty.
   */
  public Optional<UserRow> getUser(long userID, byte[] jobID) throws Exception {
    log.trace("#getUser(userID={}, jobID={})", userID, jobID);

    return new SelectUser(connection, userID, jobID).run();
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
    log.trace("#updateJobOwner(oldUserID={}, newUserID={})", oldUserID, newUserID);

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
    log.trace("#userIsGuest(userID={})", userID);

    return new SelectUserIsGuest(connection, userID).run();
  }

  /**
   * Attempts to retrieve a job by it's hash.
   */
  public Optional<FullJobRow> getJob(byte[] jobID) throws Exception {
    log.trace("#getJob(jobID={})", jobID);

    return new SelectJob(jobID).execute(connection);
  }

  /**
   * Retrieves a list of all child-to-parent job links for the job identified by
   * the given hash value.
   *
   * @param parentHash Hash of the job for which child links should be
   *                   retrieved.
   *
   * @return A set of zero or more child links.
   */
  public Set<JobLink> getJobLinks(byte[] parentHash) throws Exception {
    log.trace("#getJobLinks(parentHash={})", parentHash);

    return new SelectLinksByParent(connection, parentHash).run();
  }

  /**
   * Looks up whether the user identified by {@code userID} is linked to the job
   * identified by {@code jobHash}.
   *
   * @param userID  ID of the user being checked.
   * @param jobHash Hash of the job being checked against.
   *
   * @return {@code true} if the user has a link to the job identified by
   * {@code jobHash}, else {@code false}.
   */
  public boolean userIsLinkedToJob(long userID, byte[] jobHash) throws Exception {
    log.trace("#userIsLinkedToJob(userID={}, jobHash={})", userID, jobHash);

    return new SelectUserIsLinkedToJob(connection, userID, jobHash).run();
  }

  /**
   * Creates a new link from a child job to a parent job.
   *
   * @param jobHash    Hash of the child job to link.
   * @param parentHash Hash of the parent that the child job will be linked to.
   */
  public void createJobLink(byte[] jobHash, byte[] parentHash) throws Exception {
    log.trace("#createJobLink(jobHash={}, parentHash={})", jobHash, parentHash);

    new InsertJobLink(connection, jobHash, parentHash).run();
  }

  /**
   * Updates the expiration date of the job identified by {@code jobID} to the
   * given new timestamp value {@code end}.
   *
   * @param jobID Hash of the job for which the expiration date will be updated.
   * @param end   New expiration date for the target job.
   */
  public void updateJobDeleteTimer(byte[] jobID, OffsetDateTime end) throws Exception {
    log.trace("#updateJobDeleteTimer(jobID={}, end={})", jobID, end);

    new UpdateJobDeleteDateQuery(connection, jobID, end).run();
  }

  /**
   * Updates the queue ID for the job identified by the given job hash.
   *
   * @param jobID   Hash of the job to update.
   * @param queueID New queue ID.
   */
  public void updateJobQueueID(byte[] jobID, int queueID) throws Exception {
    log.trace("#updateJobQueueID(jobID={}, queueID={})", jobID, queueID);

    new UpdateJobQueueIDQuery(connection, jobID, queueID).run();
  }

  public void linkUserToJob(UserRow user) throws Exception {
    log.trace("#linkUserToJob(user={})", user);

    new InsertUserQuery(connection, user).run();
  }

  public void updateLinkIsPrimary(long userID, byte[] jobHash) throws Exception {
    log.trace("#updateLinkIsPrimary(userID={}, jobHash={})", userID, jobHash);

    new UpdateJobRunDirectly(connection, true, userID, jobHash).run();
  }

  /**
   * Inserts entries for a new job linked to the given user targeting the given
   * files.
   *
   * @param job     Job to insert.
   * @param user    User the job will be linked to.
   * @param targets Target files for the job.
   */
  public void registerJob(FullJobRow job, UserRow user, List<JobTarget> targets) throws Exception {
    log.trace("#registerJob(job={}, user={}, targets={})", job, user, targets);

    new InsertJobQuery(connection, job).run();
    new InsertUserQuery(connection, user).run();
    new InsertJobTargets(connection, targets).run();
  }

  /**
   * Updates the status field on the job identified by the given job hash to the
   * given {@code status} value.
   *
   * @param jobID  Hash of the job to update.
   * @param status New status for the job.
   */
  public void updateJobStatus(byte[] jobID, DBJobStatus status) throws Exception {
    log.trace("#updateJobStatus(jobID={}, status={})", jobID, status);

    new UpdateJobStatus(connection, jobID, status).run();
  }

  /**
   * Retrieves a map of all "top level" job-to-job links for a user keyed on the
   * hex encoded parent job hash.
   *
   * @param userID ID of the user whose parent jobs should be retrieved.
   *
   * @return A map of zero or more parent job hashes mapped to a list of
   * job-to-job links.
   */
  public Map<String, List<JobLink>> getAllParentJobs(long userID) throws Exception {
    log.trace("#getAllParentJobs(userID={})", userID);

    return new SelectLinksByUser(connection, userID).run();
  }

  /**
   * Retrieves all job targets for all jobs owned by the user identified by the
   * given ID.
   * <p>
   * Results are keyed on the hex encoded job ID string to which they belong.
   *
   * @param userID ID of the user whose job targets should be retrieved.
   *
   * @return A map of zero or more jobs mapped to lists of one or more target
   * files.  Note: This map size will only be zero if the given user has no
   * jobs.
   */
  public Map<String, List<JobTarget>> getJobTargetsFor(long userID) throws Exception {
    log.trace("#getJobTargetsFor(userID={})", userID);

    return new SelectTargetsByUser(connection, userID).run();
  }

  /**
   * Retrieves a collection of the jobs associated with the given user ID
   * including additional user specific metadata.
   *
   * @param userID ID of the user whose jobs should be retrieved.
   *
   * @return A collection of jobs associated with the given user ID.
   */
  public Collection<ShortUserJobRow> getUserJobs(long userID) throws Exception {
    log.trace("#getUserJobs(userID={})", userID);

    return new SelectShortUserJobsByUser(connection, userID).run();
  }

  /**
   * Retrieves a list of target files for the job identified by the given
   * job hash.
   *
   * @param jobID Hash of the job whose target files are desired.
   *
   * @return A list of zero or more target file entries.  Note: The length of
   * this will only be zero if no jobs were found matching the given job hash.
   */
  public List<JobTarget> getJobTargetsFor(byte[] jobID) throws Exception {
    log.trace("#getJobTargetsFor(jobID={})", jobID);

    return new SelectTargetsByJob(connection, jobID).run();
  }

  /**
   * Retrieves a list of "parent" jobs that contain the job identified by the
   * given hash value.  Results are further filtered to jobs owned by the given
   * user ID.
   *
   * @param childHash Hash of the job for which parent jobs should be retrieved.
   * @param userID    Used to filter results down to only jobs owned by the user
   *                  identified by this ID.
   *
   * @return A list of 0 or more parent jobs.
   */
  public List<JobLink> getParentJobs(byte[] childHash, long userID) throws Exception {
    log.trace("#getParentJobs(childHash={}, userID={})", childHash, userID);

    return new SelectParentJobs(connection, childHash, userID).run();
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
  public Optional<FullUserJobRow> getUserJob(byte[] jobID, long userID) throws Exception {
    log.trace("#getUserJob(jobID={}, userID={})", jobID, userID);

    return new SelectFullUserJob(connection, jobID, userID).run();
  }

  /**
   * Retrieves a list of jobs whose expiration date is before the given
   * {@code asOf} value.
   *
   * @param asOf Cutoff timestamp, all jobs whose expiration date is before this
   *             value will be returned.
   *
   * @return A collection of 0 or more jobs that expired before {@code asOf}.
   */
  public Collection<FullJobRow> getStaleJobs(OffsetDateTime asOf) throws Exception {
    log.trace("#getStaleJobs(asOf={})", asOf);

    return new SelectStaleJobsQuery(asOf).run();
  }

  /**
   * Deletes a job and all associated users from the WDK user database.
   *
   * @param jobID ID of the job to delete.
   */
  public void deleteJob(byte[] jobID) throws Exception {
    log.trace("#deleteJob(jobID={})", jobID);

    new DeleteUsersByJobQuery(jobID, connection).run();
    new DeleteJobQuery(connection, jobID).run();
  }

  /**
   * Deletes user-to-job links for guest user jobs that have expired.
   */
  public void deleteStaleGuests() throws Exception {
    log.trace("#deleteStaleGuests()");

    new DeleteStaleGuestsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  public void deleteJobToJobLinks(byte[] jobID) throws Exception {
    log.trace("#deleteJobToJobLinks(jobID={})", jobID);
    new DeleteJobToJobLinks(connection, jobID).run();
  }

  public void deleteJobToTargetLinks(byte[] jobID) throws Exception {
    log.trace("#deleteJobToTargetLinks(jobID={})", jobID);
    new DeleteJobToTargetLinks(connection, jobID).run();
  }

  public List<byte[]> getOrphanedJobs() throws Exception {
    log.trace("#getOrphanedJobs()");
    return new SelectOrphanedJobs(connection).run();
  }

  @Override
  public void close() throws Exception {
    this.connection.close();
  }
}
