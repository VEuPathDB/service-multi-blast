package mb.lib.db.model

import org.veupathdb.lib.hash_id.HashID

interface JobLink
{
  val childID:  HashID
  val parentID: HashID
  val position: Int
}
