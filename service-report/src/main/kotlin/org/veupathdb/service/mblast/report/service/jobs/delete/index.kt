package org.veupathdb.service.mblast.report.service.jobs.delete

import jakarta.ws.rs.NotFoundException
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.ReportDB

fun DeleteJob(reportJobID: HashID, userID: Long) {
  ReportDB.selectReportJob(reportJobID, userID) ?: throw NotFoundException()
  ReportDB.deleteUserLink(reportJobID, userID)
}