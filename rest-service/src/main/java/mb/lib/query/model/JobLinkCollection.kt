package mb.lib.query.model

import mb.lib.model.HashID

data class JobLinkCollection(
  val byParentID: MutableMap<HashID, MutableList<BlastJobLink>> = HashMap(),
  val byChildID:  MutableMap<HashID, MutableList<BlastJobLink>> = HashMap(),
)
