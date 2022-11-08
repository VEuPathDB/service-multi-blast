package org.veupathdb.service.mblast.report.jobs

import org.veupathdb.lib.compute.platform.job.JobExecutorContext
import org.veupathdb.lib.compute.platform.job.JobExecutorFactory

class ExecutorFactory : JobExecutorFactory {
  override fun newJobExecutor(ctx: JobExecutorContext) = FormatExecutor()
}