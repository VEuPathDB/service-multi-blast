package mb.lib.query

import mb.lib.db.MBlastDBManager
import mb.lib.model.JobStatus
import mb.lib.query.db.*
import mb.lib.query.model.*
import mb.lib.util.logger
import org.veupathdb.lib.hash_id.HashID

class BlastDBManager: MBlastDBManager()
{
  private val Log = logger()

  /**
   * Retrieves a blast job record from the DB if such a row exists.
   *
   * @param jobID ID of the blast job record to retrieve.
   *
   * @return An option containing the target row, if such a row exists,
   * otherwise an empty option.
   */
  fun getBlastRow(jobID: HashID): BlastRow? {
    Log.trace("#getBlastRow(jobID={})", jobID)
    return SelectBlastJob(connection, jobID).run()
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
  fun getUserBlastRow(jobID: HashID, userID: Long): UserBlastRow? {
    Log.trace("#getUserBlastRow(jobID={}, userID={})", jobID, userID)
    return SelectUserBlastJob(connection, jobID, userID).run()
  }

  /**
   * Deletes a link from a user to a blast job if such a link exists.
   *
   * @param jobID  ID of the job to unlink the user from.
   * @param userID ID of the user to unlink.
   */
  fun unlinkUser(jobID: HashID, userID: Long) {
    Log.trace("#unlinkUser(jobID={}, userID={})", jobID, userID)
    DeleteUserLink(connection, jobID, userID).run()
  }

  /**
   * Creates a link from a user to a blast job.
   *
   * @param row A blast row containing at least the job ID and the user ID.
   */
  fun linkUser(row: UserBlastRow) {
    Log.trace("#linkUser(row={})", row)
    InsertUserLink(connection, row).run()
  }

  fun linkTargets(jobID: HashID, vararg targets: JobTarget) {
    Log.trace("#linkTargets(jobID={}, targets={})", jobID, targets)
    InsertBlastTargets(connection, jobID, targets).run()
  }

  fun linkJobs(child: HashID, parent: HashID) {
    Log.trace("#linkJobs(child={}, parent={})", child, parent)
    InsertBlastJobLink(connection, child, parent).run()
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
  fun getTargetLinks(jobID: HashID): List<BlastTargetLink> {
    Log.trace("#getTargetLinks(jobID={})", jobID)
    return SelectTargetLinks(connection, jobID).run()
  }

  /**
   * Retrieves a list of child job links for the given parent job ID.
   *
   * @param parentJobID ID of the parent job for which child links should be
   *                    retrieved.
   *
   * @return A list of 0 or more child job links.
   */
  fun getChildJobLinks(parentJobID: HashID): List<BlastJobLink> {
    Log.trace("#getChildJobLinks(parentJobID={})", parentJobID)
    return SelectChildBlastLinks(connection, parentJobID).run()
  }

  /**
   * Retrieves a list of parent job links for the given child job ID.
   *
   * @param childJobID ID of the child job for which parent links should be
   *                   retrieved.
   *
   * @return A list of 0 or more parent job links.
   */
  fun getParentJobLinks(childJobID: HashID): List<BlastJobLink> {
    Log.trace("#getParentJobLinks(childJobID={})", childJobID)
    return SelectParentJobLinks(connection, childJobID).run()
  }

  /**
   * Updates the status field for a single job by ID.
   *
   * @param jobID     ID of the job to update.
   * @param newStatus The new status to be set.
   */
  fun updateJobStatus(jobID: HashID, queueID: Int, newStatus: JobStatus) {
    Log.trace("#updateJobStatus(jobID={}, queueID={}, newStatus={})", jobID, queueID, newStatus)
    UpdateJobStatus(connection, jobID, queueID, newStatus).run()
  }

  fun insertJob(job: BlastRow) {
    Log.trace("#insertJob(job={})", job)
    InsertBlastJob(connection, job).run()
  }

  /**
   * Retrieves a list of all blast jobs linked to the given user ID.
   *
   * @param userID ID of the user for which all blast jobs should be returned.
   *
   * @return A list of 0 or more matching blast jobs.
   */
  fun getUserJobs(userID: Long): List<UserBlastRow> {
    Log.trace("#getUserJobs(userID={})", userID)
    return SelectUserBlastJobs(connection, userID).run()
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
  fun getUserJobLinks(userID: Long): JobLinkCollection {
    Log.trace("#getUserJobLinks(userID={})", userID)
    return SelectUserJobLinks(connection, userID).run()
  }

  fun getUserTargetLinks(userID: Long): Map<HashID, List<BlastTargetLink>> {
    Log.trace("#getUserTargetLinks(userID={})", userID)
    return SelectUserTargetLinks(connection, userID).run()
  }

  fun getBlastQuery(jobID: HashID): String? {
    Log.trace("#getJobQuery(jobID={})", jobID)
    return SelectBlastQuery(connection, jobID).run()
  }
}
