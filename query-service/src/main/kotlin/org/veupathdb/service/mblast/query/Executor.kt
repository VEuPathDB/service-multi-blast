package org.veupathdb.service.mblast.query

import org.veupathdb.lib.compute.platform.job.JobContext
import org.veupathdb.lib.compute.platform.job.JobExecutor
import org.veupathdb.lib.compute.platform.job.JobResult

private const val QueryFileName = "query.txt"
private const val StdErrFileName = "stderr.txt"
private const val ReportFileName = "report.asn1"


class Executor : JobExecutor {
  override fun execute(ctx: JobContext): JobResult {
    // TODO: Parse config (should this be the stored, or translated config)
    // TODO: Run process built from config
    // TODO: persist outputs

    TODO()
  }
}