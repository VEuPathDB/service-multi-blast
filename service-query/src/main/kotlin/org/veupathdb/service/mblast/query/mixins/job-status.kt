package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.lib.compute.platform.job.JobStatus
import org.veupathdb.service.mblast.query.generated.model.JobStatus as IOJobStatus


/**
 * Translates the internal `JobStatus` type to the external `JobStatus` type.
 */
fun JobStatus.toIOType() =
  when (this) {
    JobStatus.Queued     -> IOJobStatus.QUEUED
    JobStatus.InProgress -> IOJobStatus.INPROGRESS
    JobStatus.Complete   -> IOJobStatus.COMPLETE
    JobStatus.Failed     -> IOJobStatus.FAILED
    JobStatus.Expired    -> IOJobStatus.EXPIRED
  }