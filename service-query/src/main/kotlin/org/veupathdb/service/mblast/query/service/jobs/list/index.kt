package org.veupathdb.service.mblast.query.service.jobs.list

import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.service.mblast.query.generated.model.JobStatus
import org.veupathdb.service.mblast.query.generated.model.QueryJobListEntry
import org.veupathdb.service.mblast.query.generated.model.QueryJobListEntryImpl
import org.veupathdb.service.mblast.query.mixins.toIOType
import org.veupathdb.service.mblast.query.mixins.toTargetSite
import org.veupathdb.service.mblast.query.mixins.toUserMeta
import org.veupathdb.service.mblast.query.model.FullParentUserQueryConfig
import org.veupathdb.service.mblast.query.sql.JobDBManager

/**
 * Retrieves and returns the list of query jobs linked to the given user ID.
 *
 * @param userID ID of the user whose jobs should be fetched.
 *
 * @return A list of the user's jobs.
 */
fun ListUserJobs(userID: Long, site: String?): List<QueryJobListEntry> =
  // Get the jobs from the persistent database.
  JobDBManager.getFullUserJobs(userID, site)
    // stream over the job records
    .stream()
    // parallelize because we wil be making IO calls
    .parallel()
    // translate the config
    .map { it.convertToIORecord() }
    // Back to a list again :(
    .toList()

/**
 * Converts the target `FullParentUserQueryConfig` record into the IO type
 * [QueryJobListEntry].
 *
 * To convert types, the status of the job must be retrieved from the platform.
 */
private fun FullParentUserQueryConfig.convertToIORecord() =
  QueryJobListEntryImpl().also {
    it.queryJobID = queryJobID.string
    it.status     = lookupStatus()
    it.site       = projectID.toTargetSite()
    it.userMeta   = toUserMeta()
  }

private fun FullParentUserQueryConfig.lookupStatus() =
  AsyncPlatform.getJob(queryJobID)?.status?.toIOType() ?: JobStatus.EXPIRED
