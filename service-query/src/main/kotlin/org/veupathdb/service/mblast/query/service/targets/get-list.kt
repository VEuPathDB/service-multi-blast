package org.veupathdb.service.mblast.query.service.targets

import org.veupathdb.service.mblast.query.generated.model.BlastTargetIndex
import org.veupathdb.service.mblast.query.generated.model.BlastTargetIndexImpl
import org.veupathdb.service.mblast.query.generated.model.BlastTargetMapImpl
import org.veupathdb.service.mblast.query.generated.model.BlastableTargetImpl
import org.veupathdb.service.mblast.query.service.BlastTargetManager

fun GetTargets(): BlastTargetIndex {
  val siteIndex = BlastTargetIndexImpl()

  BlastTargetManager.indexBlastTargets()
    .forEach { (site, targets) ->
      val targetIndex = BlastTargetMapImpl()

      targets.forEach { (target, index) ->
        val dbIndex = BlastableTargetImpl()

        dbIndex.aaTargets = index.aaTargets
        dbIndex.naTargets = index.naTargets

        targetIndex.setAdditionalProperties(target, dbIndex)
      }

      siteIndex.setAdditionalProperties(site, targetIndex)
    }

  return siteIndex
}