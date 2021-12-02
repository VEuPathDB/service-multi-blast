package mb.lib.query.model

import mb.lib.model.HashID

data class BlastJobLink(val childJobID: HashID, val parentJobID: HashID, val position: Int)
