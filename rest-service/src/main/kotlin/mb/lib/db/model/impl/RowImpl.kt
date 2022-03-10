package mb.lib.db.model.impl

import mb.lib.db.model.Row
import org.veupathdb.lib.hash_id.HashID

open class RowImpl(override val jobID: HashID): Row
