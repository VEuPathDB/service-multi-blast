package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.lib.compute.platform.job.AsyncJob
import org.veupathdb.service.mblast.query.generated.model.QueryJobDetails
import org.veupathdb.service.mblast.query.generated.model.QueryJobUserMeta
import org.veupathdb.service.mblast.query.generated.model.QueryJobUserMetaImpl
import org.veupathdb.service.mblast.query.model.FullParentQueryConfig
import org.veupathdb.service.mblast.query.model.FullParentUserQueryConfig

/**
 * Converts the target config into a form suitable for sending to the HTTP
 * client.
 *
 * @receiver Config to translate.
 *
 * @param s3Job S3 details about the job the configuration represents containing
 * additional details required to construct the IO type.
 *
 * @return IO config to return to the HTTP client.
 */
fun FullParentUserQueryConfig.toIODetails(s3Job: AsyncJob?): QueryJobDetails =
  (this as FullParentQueryConfig).toIODetails(s3Job).also {
    it.userMeta = toUserMeta()
  }


/**
 * Parses user metadata out of the target config into an optional form suitable
 * for sending to the HTTP client.
 *
 * @receiver Config to translate.
 *
 * @return An IO user meta object, if there is user meta to return, otherwise
 * `null`.
 */
fun FullParentUserQueryConfig.toUserMeta(): QueryJobUserMeta? =
  if (summary.isNullOrBlank() && description.isNullOrBlank())
    null
  else
    QueryJobUserMetaImpl().also {
      it.summary = summary
      it.description = description
    }
