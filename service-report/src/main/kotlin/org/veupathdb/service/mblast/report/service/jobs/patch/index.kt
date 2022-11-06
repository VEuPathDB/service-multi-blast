package org.veupathdb.service.mblast.report.service.jobs.patch

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.db.ReportDB
import org.veupathdb.service.mblast.report.generated.model.ReportJobPatchRequest
import org.veupathdb.service.mblast.report.model.UserMeta

fun PatchUserJob(reportJobID: HashID, userID: Long, entity: ReportJobPatchRequest) {
  if (!entity.hasSomethingToSet())
    return

  entity.validate()

  val job = ReportDB.selectReportJob(reportJobID, userID)
    ?: throw NotFoundException()

  val newMeta = UserMeta(
    userID,
    entity.userMeta.summary ?: job.userSummary,
    entity.userMeta.description ?: job.userDescription,
  )

  ReportDB.withTransaction {
    it.updateUserMeta(reportJobID, newMeta)
  }
}

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