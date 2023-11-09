package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.service.mblast.query.blast.convert.toExternal
import org.veupathdb.service.mblast.query.generated.model.JobStatus
import org.veupathdb.service.mblast.query.generated.model.QueryJobDetails
import org.veupathdb.service.mblast.query.generated.model.QueryJobDetailsImpl
import org.veupathdb.service.mblast.query.model.FullParentQueryConfig
import java.util.*

/**
 * Translates the target config into a form suitable for sending to the HTTP
 * client.
 *
 * @receiver Config to translate.
 *
 * @param s3Job S3 details about the job the configuration represents containing
 * additional details required to construct the IO type.
 *
 * @return IO config to return to the HTTP client.
 */
fun FullParentQueryConfig.toIODetails(s3Job: AsyncJob?): QueryJobDetails =
  QueryJobDetailsImpl().also {
    it.queryJobID  = queryJobID.string
    it.status      = s3Job?.status?.toIOType() ?: JobStatus.EXPIRED
    it.blastConfig = config.toExternal()
    it.createdOn   = it.createdOn
    it.jobConfig   = toJobConfig()
    it.subJobs     = childJobs.map { it.queryJobID.string }
  }

