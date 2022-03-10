package mb.lib.query.model

import org.veupathdb.lib.hash_id.HashID

data class BlastTargetLink(val jobID: HashID, val organism: String, val targetFile: String)
