package org.veupathdb.service.mblast.report.service.jobs.patch

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.ReportJobPatchRequest
import org.veupathdb.service.mblast.report.model.UserMeta

/**
 * Updates a user to job link with the new meta provided in the PATCH request.
 *
 * Values in the PATCH request that are `null` will not be updated.
 *
 * @param reportJobID ID of the job the link to update targets.
 *
 * @param userID ID of the user whose link should be updated.
 *
 * @param entity PATCH request payload.
 */
fun PatchUserJob(reportJobID: HashID, userID: Long, entity: ReportJobPatchRequest) {
  // Verify that the request body actually contained something.
  if (!entity.hasSomethingToSet())
    return

  // Validate the request body.
  entity.validate()

  // Lookup the user-to-job link.
  val job = ReportDB.selectReportJob(reportJobID, userID)
    ?: throw NotFoundException()

  // Create the new metadata to set, defaulting null values back to their
  // currently saved values (which may be null).
  val newMeta = UserMeta(
    userID,
    entity.userMeta.summary ?: job.userSummary,
    entity.userMeta.description ?: job.userDescription,
  )

  // Update the user link in the database.
  ReportDB.withTransaction { it.updateUserMeta(reportJobID, newMeta) }
}

/**
 * Tests whether the request body contains values to set on the user link.
 */
private fun ReportJobPatchRequest.hasSomethingToSet() =
  when {
    userMeta == null                                         -> false
    userMeta.summary == null && userMeta.description == null -> false
    else                                                     -> true
  }

private fun ReportJobPatchRequest.validate() {
  if (userMeta.summary != null && userMeta.summary.length > Const.MAX_USER_SUMMARY_LENGTH)
    throw UnprocessableEntityException(mapOf(
      "userMeta.summary" to listOf("cannot be greater than ${Const.MAX_USER_SUMMARY_LENGTH} characters in length")
    ))
}