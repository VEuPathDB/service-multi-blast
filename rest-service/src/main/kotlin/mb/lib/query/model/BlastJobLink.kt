package mb.lib.query.model

import org.veupathdb.lib.hash_id.HashID

data class BlastJobLink(val childJobID: HashID, val parentJobID: HashID, val position: Int)
