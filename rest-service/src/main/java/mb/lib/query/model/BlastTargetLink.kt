package mb.lib.query.model

import mb.lib.model.HashID

data class BlastTargetLink(val jobID: HashID, val organism: String, val targetFile: String)
