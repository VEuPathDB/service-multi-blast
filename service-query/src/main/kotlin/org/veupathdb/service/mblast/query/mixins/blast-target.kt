package org.veupathdb.service.mblast.query.mixins

import org.veupathdb.service.mblast.query.generated.model.QueryJobTarget
import org.veupathdb.service.mblast.query.generated.model.QueryJobTargetImpl
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.service.BlastTargetManager


/**
 * Converts the target type into a form suitable for sending to the HTTP client.
 *
 * @receiver Target record to convert.
 *
 * @return Converted IO type suitable for sending to the HTTP client.
 */
fun BlastTarget.toIOType(): QueryJobTarget =
  QueryJobTargetImpl().also {
    it.targetDisplayName = displayName
    it.targetFile        = fileName
  }

/**
 * Transforms the target list of [BlastTarget] instances into a list of path
 * strings.
 */
fun List<BlastTarget>.toDBFiles(site: String) =
  map { BlastTargetManager.dbPath(site, it.displayName, it.fileName) }
