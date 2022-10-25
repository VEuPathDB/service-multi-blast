package org.veupathdb.service.mblast.query.service.jobs.submit.model

import mblast.query.pipe.SequenceFileWriteResult
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.model.QueryUserMeta

data class JobSubmission(
  val parentJobID:     HashID,
  val blastConfig:     BlastConfigPair,
  val blastTargets:    List<BlastTarget>,
  val projectID:       String,
  val queryFiles:      SequenceFileWriteResult,
  val addToUserJobs:   Boolean,
  val userMeta:        QueryUserMeta
) : AutoCloseable {

  override fun close() {
    queryFiles.deleteAll()
  }

  companion object {
    @JvmStatic
    fun build(fn: JobSubmissionBuilder.() -> Unit): JobSubmission {
      val builder = JobSubmissionBuilder()

      try {
        builder.fn()
      } catch (e: Throwable) {
        if (builder.queryFiles != null)
          builder.queryFiles!!.deleteAll()

        throw e
      }

      return JobSubmission(
        builder.parentJobID!!,
        BlastConfigPair(
          builder.blastConfigWDB!!,
          builder.blastConfigWODB!!
        ),
        builder.blastTargets!!,
        builder.projectID!!,
        builder.queryFiles!!,
        builder.addToUserJobs!!,
        builder.userMeta!!
      )
    }
  }
}

