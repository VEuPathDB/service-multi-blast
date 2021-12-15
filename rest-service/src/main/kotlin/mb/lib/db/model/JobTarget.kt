package mb.lib.db.model

interface JobTarget: Row
{
  val organism: String
  val targetFile: String
}
