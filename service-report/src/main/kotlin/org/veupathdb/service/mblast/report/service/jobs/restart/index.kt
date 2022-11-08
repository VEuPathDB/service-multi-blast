package org.veupathdb.service.mblast.report.service.jobs.restart

import jakarta.ws.rs.ForbiddenException
import jakarta.ws.rs.NotFoundException
import org.gusdb.fgputil.Tuples.TwoTuple
import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.service.ReportPlatform

fun RestartJob(reportJobID: HashID, authToken: TwoTuple<String, String>) {
  val reportJob = ReportPlatform.getJob(reportJobID)
    ?: throw NotFoundException()

  if (reportJob.status != JobStatus.Expired)
    throw ForbiddenException("cannot rerun a job that has not expired")

  ReportPlatform.submitJob(
    reportJobID,
    reportJob.queryJobID,
    authToken,
    reportJob.config,
  )
}