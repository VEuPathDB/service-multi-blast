package mb.lib.query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import mb.lib.db.MBlastDBManager;
import mb.lib.model.HashID;
import mb.lib.model.JobStatus;
import mb.lib.query.db.*;
import mb.lib.query.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class BlastDBManager extends MBlastDBManager
{
  private static final Logger Log = LogManager.getLogger(BlastDBManager.class);

  public BlastDBManager() throws Exception {
    Log.trace("::new()");
  }

  /**
   * Retrieves a blast job record from the DB if such a row exists.
   *
   * @param jobID ID of the blast job record to retrieve.
   *
   * @return An option containing the target row, if such a row exists,
   * otherwise an empty option.
   */
  Optional<BlastRow> getBlastRow(HashID jobID) throws Exception {
    Log.trace("#getBlastRow(jobID={})", jobID);
    return new SelectBlastJob(getConnection(), jobID).run();
  }

  /**
   * Retrieves a user blast job record from the DB if such a row exists.
   *
   * @param jobID  ID of the blast job record to retrieve.
   * @param userID ID of the user whose data to retrieve.
   *
   * @return An option containing the target row, if such a row exists,
   * otherwise an empty option.
   */
  Optional<UserBlastRow> getUserBlastRow(HashID jobID, long userID) throws Exception {
    Log.trace("#getUserBlastRow(jobID={}, userID={})", jobID, userID);
    return new SelectUserBlastJob(getConnection(), jobID, userID).run();
  }

  /**
   * Deletes a link from a user to a blast job if such a link exists.
   *
   * @param jobID  ID of the job to unlink the user from.
   * @param userID ID of the user to unlink.
   */
  void unlinkUser(HashID jobID, long userID) throws Exception {
    Log.trace("#unlinkUser(jobID={}, userID={})", jobID, userID);
    new DeleteUserLink(getConnection(), jobID, userID).run();
  }

  /**
   * Creates a link from a user to a blast job.
   *
   * @param row A blast row containing at least the job ID and the user ID.
   */
  void linkUser(UserBlastRow row) throws Exception {
    Log.trace("#linkUser(row={})", row);
    new InsertUserLink(getConnection(), row).run();
  }

  /**
   * Retrieves a list of target blast DB files linked to the job identified by
   * {@code jobID}.
   *
   * @param jobID ID of the job to retrieve target links for.
   *
   * @return A list of 0 or more target links for the given {@code jobID}.  This
   * list should only be empty in the case where no job exists with the given
   * {@code jobID}.
   */
  List<BlastTargetLink> getTargetLinks(HashID jobID) throws Exception {
    Log.trace("#getTargetLinks(jobID={})", jobID);
    return new SelectTargetLinks(getConnection(), jobID).run();
  }

  /**
   * Retrieves a list of child job links for the given parent job ID.
   *
   * @param parentJobID ID of the parent job for which child links should be
   *                    retrieved.
   *
   * @return A list of 0 or more child job links.
   */
  List<BlastJobLink> getChildJobLinks(HashID parentJobID) throws Exception {
    Log.trace("#getChildJobLinks(parentJobID={})", parentJobID);
    return new SelectChildBlastLinks(getConnection(), parentJobID).run();
  }

  /**
   * Retrieves a list of parent job links for the given child job ID.
   *
   * @param childJobID ID of the child job for which parent links should be
   *                   retrieved.
   *
   * @return A list of 0 or more parent job links.
   */
  List<BlastJobLink> getParentJobLinks(HashID childJobID) throws Exception {
    Log.trace("#getParentJobLinks(childJobID={})", childJobID);
    return new SelectParentJobLinks(getConnection(), childJobID).run();
  }

  /**
   * Updates the status field for a single job by ID.
   *
   * @param jobID     ID of the job to update.
   * @param newStatus The new status to be set.
   */
  void updateJobQueue(HashID jobID, int queueID, JobStatus newStatus) throws Exception {
    Log.trace("#updateJobStatus(jobID={}, queueID={}, newStatus={})", jobID, queueID, newStatus);
    new UpdateJobStatus(getConnection(), jobID, queueID, newStatus).run();
  }

  void insertJob(BlastRow job) throws Exception {
    Log.trace("#insertJob(job={})", job);
    new InsertBlastJob(getConnection(), job).run();
  }

  /**
   * Retrieves a list of all blast jobs linked to the given user ID.
   *
   * @param userID ID of the user for which all blast jobs should be returned.
   *
   * @return A list of 0 or more matching blast jobs.
   */
  List<UserBlastRow> getUserJobs(long userID) throws Exception {
    Log.trace("#getUserJobs(userID={})", userID);
    return new SelectUserBlastJobs(getConnection(), userID).run();
  }

  /**
   * Retrieves a mapping of all job-to-job links associated with the given user
   * ID.
   *
   * @param userID ID of the user for which job links should be retrieved.
   *
   * @return A collection of job-to-job links mapped by both child and parent
   * job IDs.
   */
  JobLinkCollection getUserJobLinks(long userID) throws Exception {
    Log.trace("#getUserJobLinks(userID={})", userID);
    return new SelectUserJobLinks(getConnection(), userID).run();
  }

  Map<HashID, List<BlastTargetLink>> getUserTargetLinks(long userID) throws Exception {
    Log.trace("#getUserTargetLinks(userID={})", userID);
    return new SelectUserTargetLinks(getConnection(), userID).run();
  }

  Optional<String> getBlastQuery(HashID jobID) throws Exception {
    Log.trace("#getJobQuery(jobID={})", jobID);
    return new SelectBlastQuery(getConnection(), jobID).run();
  }

  @Override
  public String toString() {
    return "BlastDBManager{}";
  }
}
