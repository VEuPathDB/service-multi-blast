package org.veupathdb.service.mblast.report.ext

import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.service.mblast.report.generated.model.JobStatus as IOJobStatus

fun JobStatus.toIOStatus() =
  when (this) {
    JobStatus.Queued     -> IOJobStatus.QUEUED
    JobStatus.InProgress -> IOJobStatus.INPROGRESS
    JobStatus.Complete   -> IOJobStatus.COMPLETE
    JobStatus.Failed     -> IOJobStatus.FAILED
    JobStatus.Expired    -> IOJobStatus.EXPIRED
  }