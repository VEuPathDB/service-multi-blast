package mb.lib.query.model

open class FullUserBlastRow: UserBlastRow
{
  var targetDBs:  List<BlastTargetLink>? = null
  var childJobs:  List<BlastJobLink>?    = null
  var parentJobs: List<BlastJobLink>?    = null

  constructor(row: UserBlastRow): super(row)

  constructor(
    row: BlastRow,
    userID: Long,
    description: String? = null,
    maxDownloadSize: Long = 0,
    runDirectly: Boolean = false,
  ): super(row, userID, description, maxDownloadSize, runDirectly)
}
