package org.veupathdb.service.mblast.report.ext

import org.veupathdb.service.mblast.report.convert.toExternal
import org.veupathdb.service.mblast.report.generated.model.*
import org.veupathdb.service.mblast.report.model.UserReportDetails
import org.veupathdb.service.mblast.report.service.ReportPlatform

fun UserReportDetails.toIODetails(): ReportJobDetails {
  return ReportJobDetailsImpl().also {
    it.reportJobID = reportJobID.string
    it.queryJobID  = queryJobID.string
    it.blastConfig = config.toExternal()
    it.userMeta    = toUserMeta()
    it.status      = ReportPlatform.getJobStatus(reportJobID).toIOStatus()
  }
}


fun UserReportDetails.toListEntry(): ReportJobListEntry =
  ReportJobListEntryImpl().also {
    it.queryJobID = queryJobID.string
    it.reportJobID = reportJobID.string
    it.userMeta = toUserMeta()
  }


fun UserReportDetails.toUserMeta(): ReportJobUserMeta? =
  if (userSummary == null && userDescription == null)
    null
  else
    ReportJobUserMetaImpl().also {
      it.summary = userSummary
      it.description = userDescription
    }