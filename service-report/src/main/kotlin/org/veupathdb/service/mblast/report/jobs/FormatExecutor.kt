package org.veupathdb.service.mblast.report.jobs

import com.fasterxml.jackson.databind.node.ObjectNode
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.ThreadContext
import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.formatter.BlastFormatter
import org.veupathdb.lib.compute.platform.job.JobContext
import org.veupathdb.lib.compute.platform.job.JobExecutor
import org.veupathdb.lib.compute.platform.job.JobResult
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.ext.makeOutFileName
import org.veupathdb.service.mblast.report.model.JobConfig
import org.veupathdb.service.mblast.report.service.mblast.MBlastQuerySvc
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.io.path.listDirectoryEntries
import kotlin.io.path.name

class FormatExecutor : JobExecutor {
  private val logger = LogManager.getLogger(javaClass)

  override fun execute(ctx: JobContext): JobResult {
    ThreadContext.put(Const.THREAD_CONTEXT_JOB_ID, ctx.jobID.string)

    try {
      val blastConfig = ctx.workspace
        .openStream(Const.CONFIG_FILE)
        .use { Blast.blastFormatter(Json.parse<ObjectNode>(it)) }

      val jobConfig = Json.parse<JobConfig>(ctx.config!!)

      blastConfig.outFile(blastConfig.makeOutFileName())
      blastConfig.archive(Const.ARCHIVE_FILE)

      return execute(ctx, jobConfig, blastConfig)
    } finally {
      ThreadContext.remove(Const.THREAD_CONTEXT_JOB_ID)
    }
  }

  private fun execute(ctx: JobContext, config: JobConfig, blast: BlastFormatter): JobResult {
    logger.debug("Downloading result for query job {}", config.queryID)
    // Download the report that we will be formatting from the query service
    MBlastQuerySvc.getQueryResult(config.queryID, config.userAuth)
      .use { ctx.workspace.write(Const.ARCHIVE_FILE, it) }


    // Get a list of the files in the directory before we run the blast
    // formatter tool.
    val fileListBefore = ctx.getWorkspaceFileList()

    // Run the blast formatter
    if (!runFormatter(ctx, blast))
      return JobResult.failure(Const.STD_ERR_FILE_NAME)

    // Get a list of the files in the directory after we run the blast formatter
    // tool.
    val fileListAfter = ctx.getWorkspaceFileList()

    // Remove the list of files that were there before the blast formatter tool
    // was run to give us a list of just the new files.
    fileListAfter.removeAll(fileListBefore)
    fileListAfter.remove("stderr.txt")

    logger.debug("zipping {} output file(s)", fileListAfter.size)

    // Create a zip file containing the files created by the BLAST+ execution.
    ctx.workspace.touch(Const.OUTPUT_ZIP_NAME)
      .toFile()
      .outputStream()
      .buffered()
      .let { ZipOutputStream(it) }
      .use { zos ->
        // Set the zip compression to the max level
        zos.setLevel(9)

        // Add each output file to the zip.
        fileListAfter.forEach { fileName ->
          zos.putNextEntry(ZipEntry(fileName))
          ctx.workspace.openStream(fileName)
            .use { it -> it.transferTo(zos) }
          zos.closeEntry()
        }
      }

    // Add the other files that we want to upload to S3 to the list as we are
    // going to use this list as our upload list now.
    fileListAfter.add(Const.STD_ERR_FILE_NAME)
    fileListAfter.add(Const.OUTPUT_ZIP_NAME)

    return JobResult.success(fileListAfter)
  }

  private fun runFormatter(ctx: JobContext, blast: BlastFormatter): Boolean {
    logger.info("Executing command: {}", blast.toCliString())

    val exitCode = with(
      ProcessBuilder(*blast.toCliArray())
        .directory(ctx.workspace.path.toFile())
        .start()
    ) {
      ctx.workspace.write(Const.STD_ERR_FILE_NAME, errorStream)
      waitFor()
    }

    logger.debug("Exit code: {}", exitCode)

    return exitCode == 0
  }


  private fun JobContext.getWorkspaceFileList() =
    workspace.path
      .listDirectoryEntries()
      .mapTo(HashSet(16)) { it.name }
}