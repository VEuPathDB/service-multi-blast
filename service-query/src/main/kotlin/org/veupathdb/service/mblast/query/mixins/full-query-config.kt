package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.service.mblast.query.generated.model.QueryJobConfig
import org.veupathdb.service.mblast.query.generated.model.QueryJobConfigImpl
import org.veupathdb.service.mblast.query.model.FullQueryConfig

/**
 * Converts the target config into a form suitable for sending to the HTTP
 * client.
 *
 * @receiver Config to translate.
 *
 * @return IO config to return to the HTTP client.
 */
fun FullQueryConfig.toJobConfig(): QueryJobConfig =
  QueryJobConfigImpl().also {
    it.site    = projectID.toTargetSite()
    it.targets = targets.map { it.toIOType() }
  }
