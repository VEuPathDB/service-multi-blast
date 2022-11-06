package org.veupathdb.service.mblast.report.ext

import org.veupathdb.service.mblast.report.convert.toExternal
import org.veupathdb.service.mblast.report.generated.model.ReportJobDetails
import org.veupathdb.service.mblast.report.generated.model.ReportJobDetailsImpl
import org.veupathdb.service.mblast.report.model.ReportDetails
import org.veupathdb.service.mblast.report.service.ReportPlatform

fun ReportDetails.toIODetails(): ReportJobDetails {
  return ReportJobDetailsImpl().also {
    it.reportJobID = reportJobID.string
    it.queryJobID  = queryJobID.string
    it.blastConfig = config.toExternal()
    it.userMeta    = null
    it.status      = ReportPlatform.getJobStatus(reportJobID).toIOStatus()
  }
}
