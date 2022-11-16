package org.veupathdb.service.mblast.query.jobs

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.ThreadContext
import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.compute.platform.job.JobContext
import org.veupathdb.lib.compute.platform.job.JobExecutor
import org.veupathdb.lib.compute.platform.job.JobResult
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.query.Const
import org.veupathdb.service.mblast.query.service.Metrics

/**
 * # BLAST+ Command Executor
 *
 * [JobExecutor] implementation for the MBlast query service that executes the
 * BLAST+ CLI tools based on the configuration and query present in a job's
 * workspace.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
class BlastExecutor : JobExecutor {
  private val logger = LogManager.getLogger(javaClass)

  override fun execute(ctx: JobContext): JobResult {
    ThreadContext.put(Const.ThreadContextJobID, ctx.jobID.string)
    try {
      return exec(ctx)
    } finally {
      ThreadContext.remove(Const.ThreadContextJobID)
    }
  }

  private fun exec(ctx: JobContext): JobResult {
    // Load the BLAST+ CLI configuration from the workspace config JSON file.
    val blastConfig = ctx.workspace
      .openStream(Const.ConfigFileName)
      .use { Blast.of(Json.parse(it)) }

    logger.info("Executing command: {}", { blastConfig.toCliString() })

    val timer = Metrics.BlastTimes
      .labels(blastConfig.tool.value)
      .startTimer()

    val exitCode = with(
      ProcessBuilder(*blastConfig.toCliArray())
        .directory(ctx.workspace.path.toFile())
        .start()
    ) {
      // Write the stderr for the BLAST+ command out to file (which will be
      // persisted to S3)
      ctx.workspace.write(Const.StdErrFileName, errorStream)
      waitFor().also { timer.observeDuration() }
    }

    logger.debug("Exit code: $exitCode")

    return if (exitCode == 0)
      JobResult.success(Const.StdErrFileName, Const.ReportFileName)
    else
      JobResult.failure(Const.StdErrFileName, Const.ReportFileName)
  }
}