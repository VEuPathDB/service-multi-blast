package mb.lib.db.model

import org.veupathdb.lib.hash_id.HashID

interface Row {
  val jobID: HashID
}