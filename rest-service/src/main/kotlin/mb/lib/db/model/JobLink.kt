package mb.lib.db.model

import mb.lib.model.HashID

interface JobLink
{
  val childID: HashID
  val parentID: HashID
  val position: Int
}
