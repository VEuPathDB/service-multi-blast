package org.veupathdb.service.mblast.query.model

interface BlastableTarget {
  val naTargets: List<String>
  val aaTargets: List<String>
}

data class BlastableTargetImpl(
  override val naTargets: MutableList<String> = ArrayList(1),
  override val aaTargets: MutableList<String> = ArrayList(1),
) : BlastableTarget