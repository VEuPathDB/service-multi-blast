package mb.lib.query.model

import org.veupathdb.lib.hash_id.HashID

data class JobLinkCollection(
  val byParentID: MutableMap<HashID, MutableList<BlastJobLink>> = HashMap(),
  val byChildID:  MutableMap<HashID, MutableList<BlastJobLink>> = HashMap(),
)
