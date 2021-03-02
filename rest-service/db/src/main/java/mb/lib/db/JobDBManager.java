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

  public void updateJobOwner(long oldUserID, long newUserID) throws Exception {
    log.trace("#updateJobOwner(oldUserID={}, newUserID={})", oldUserID, newUserID);

    new UpdateJobOwner(this.connection, oldUserID, newUserID).run();
  }

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

  public Set<JobLink> getJobLinks(byte[] parentHash) throws Exception {
    log.trace("#getJobLinks(parentHash={})", parentHash);

    return new SelectLinksByParent(connection, parentHash).run();
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  public  boolean userIsLinkedToJob(long userID, byte[] jobHash)
  throws Exception {
    log.trace("#userIsLinkedToJob(userID={}, jobHash={})", userID, jobHash);

    return new SelectUserIsLinkedToJob(connection, userID, jobHash).run();
  }

  public void createJobLink(byte[] jobHash, byte[] parentHash)
  throws Exception {
    log.trace("#createJobLink(jobHash={}, parentHash={})", jobHash, parentHash);

    new InsertJobLink(connection, jobHash, parentHash).run();
  }

  public void updateJobDeleteTimer(byte[] jobID, OffsetDateTime end)
  throws Exception {
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

  public void updateLinkIsPrimary(long userID, byte[] jobHash)
  throws Exception {
    log.trace("#updateLinkIsPrimary(userID={}, jobHash={})", userID, jobHash);

    new UpdateJobRunDirectly(connection, true, userID, jobHash).run();
  }

  public void registerJob(FullJobRow job, UserRow user, List<JobTarget> targets) throws Exception {
    log.trace("#registerJob(job={}, user={}, targets={})", job, user, targets);

    new InsertJobQuery(connection, job).run();
    new InsertUserQuery(connection, user).run();
    new InsertJobTargets(connection, targets).run();
  }

  public  void updateJobStatus(byte[] jobID, DBJobStatus status)
  throws Exception {
    log.trace("#updateJobStatus(jobID={}, status={})", jobID, status);

    new UpdateJobStatus(connection, jobID, status).run();
  }

  public Map<String, List<JobLink>> getAllParentJobs(long userID) throws Exception {
    log.trace("#getAllParentJobs(userID={})", userID);

    return new SelectLinksByUser(connection, userID).run();
  }

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

  public List<JobTarget> getJobTargetsFor(byte[] jobID) throws Exception {
    log.trace("#getJobTargetsFor(jobID={})", jobID);

    return new SelectTargetsByJob(connection, jobID).run();
  }

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
    new DeleteJobQuery(jobID, connection).run();
  }

  public void deleteStaleGuests() throws Exception {
    log.trace("#deleteStaleGuests()");

    new DeleteStaleGuestsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  public void deleteOrphanJobs() throws Exception {
    log.trace("#deleteOrphanJobs()");

    new DeleteOrphanJobsQuery(DbManager.userDatabase().getDataSource()).run();
  }

  @Override
  public void close() throws Exception {
    this.connection.close();
  }

}
