package mblast

import org.veupathdb.lib.compute.platform.job.JobExecutorContext
import org.veupathdb.lib.compute.platform.job.JobExecutorFactory

class ExecutorFactory : JobExecutorFactory {

  override fun newJobExecutor(ctx: JobExecutorContext) = Executor()
}