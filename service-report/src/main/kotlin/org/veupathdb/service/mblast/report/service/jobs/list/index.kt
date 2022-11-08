package org.veupathdb.service.mblast.report.service.jobs.list

import org.veupathdb.lib.compute.platform.AsyncPlatform
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.ext.toIOStatus
import org.veupathdb.service.mblast.report.ext.toUserMeta
import org.veupathdb.service.mblast.report.generated.model.JobStatus
import org.veupathdb.service.mblast.report.generated.model.ReportJobListEntry
import org.veupathdb.service.mblast.report.generated.model.ReportJobListEntryImpl
import org.veupathdb.service.mblast.report.model.UserReportDetails

/**
 * # Get Jobs by User ID
 *
 * Returns a list of jobs associated with the given [userID].  If the user has
 * no jobs, an empty list will be returned.
 *
 * @param userID ID of the user whose jobs should be retrieved.
 *
 * @return A list of zero or more jobs belonging to the target user.
 */
fun GetJobs(userID: Long): List<ReportJobListEntry> =
  ReportDB.selectReportJobs(userID)
    .stream()
    .map { it.toListEntry() }
    .toList()

/**
 * # Get Jobs by User and Query ID
 *
 * Returns a list of jobs associated with both the given [userID] and the query
 * job ID.  If the user has no jobs or has no jobs associated with the given
 * [queryJobID] then an empty list will be returned.
 *
 * @param queryJobID ID of the query job that results should be associated with.
 *
 * @param userID ID of the user whose jobs should be retrieved.
 *
 * @return A list of zero or more jobs.
 */
fun GetJobs(queryJobID: HashID, userID: Long): List<ReportJobListEntry> =
  ReportDB.selectReportJobs(queryJobID, userID)
    .stream()
    .map { it.toListEntry() }
    .toList()

private fun UserReportDetails.toListEntry() =
  ReportJobListEntryImpl().also {
    it.reportJobID = reportJobID.string
    it.queryJobID  = queryJobID.string
    it.status      = lookupStatus()
    it.userMeta    = toUserMeta()
  }

private fun UserReportDetails.lookupStatus() =
  AsyncPlatform.getJob(reportJobID)?.status?.toIOStatus() ?: JobStatus.EXPIRED
