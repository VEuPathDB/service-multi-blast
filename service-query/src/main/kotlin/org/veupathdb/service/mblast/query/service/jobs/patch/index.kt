package org.veupathdb.service.mblast.query.service.jobs.patch

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.generated.model.QueryJobPatchRequest
import org.veupathdb.service.mblast.query.model.QueryUserMetaImpl
import org.veupathdb.service.mblast.query.service.MBlastPlatform
import org.veupathdb.service.mblast.query.sql.JobDBManager

fun PatchUserJob(queryJobID: HashID, userID: Long, entity: QueryJobPatchRequest) {
  // Check if the entity contains any values to update with (i.e. check if they
  // sent us an empty request)
  if (!entity.hasSomethingToSet())
    return

  // So the request isn't empty.

  // Validate it.
  entity.validate()

  // Look up the user's job or fail because the user doesn't have this job.
  val (dbJob, _) = MBlastPlatform.getJob(queryJobID, userID)
    ?: throw NotFoundException()

  val newMeta = QueryUserMetaImpl(
    userID,
    entity.meta.summary ?: dbJob.summary,
    entity.meta.description ?: dbJob.description,
  )

  JobDBManager.withTransaction {
    it.updateUserMeta(queryJobID, newMeta)
  }
}

private fun QueryJobPatchRequest.hasSomethingToSet() =
  when {
    meta == null                                     -> false
    meta.summary == null && meta.description == null -> false
    else                                             -> true
  }

private fun QueryJobPatchRequest.validate() {
  if (meta.summary != null && meta.summary.length > Const.MaxSummaryLength)
    throw UnprocessableEntityException(mapOf("meta.summary" to listOf("cannot be greater than ${Const.MaxSummaryLength} characters in length")))
}