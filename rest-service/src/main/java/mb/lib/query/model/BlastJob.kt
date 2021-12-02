package mb.lib.query.model

import mb.lib.blast.model.BlastQuery
import mb.lib.model.HashID
import mb.lib.util.jsonStringify
import mb.lib.util.md5
import org.veupathdb.lib.blast.BlastQueryConfig

@Suppress("ArrayInDataClass")
data class BlastJob(
  val config:      BlastQueryConfig,
  val query:       BlastQuery,
  val site:        String,
  val description: String? = null,
  val userID:      Long,
  val maxDLSize:   Long,
  val targets:     Array<JobTarget>,
) {
  var parentID:    HashID? = null

  var isPrimary:   Boolean = true

  constructor(
    config: BlastQueryConfig,
    query: BlastQuery,
    site: String,
    description: String? = null,
    userID: Long,
    maxDLSize: Long,
    targets: Array<JobTarget>,
    parentID: HashID
  ) : this(config, query, site, description, userID, maxDLSize, targets) {
    this.parentID = parentID
    this.isPrimary = false
  }

  fun digest(query: String): HashID {
    try {
      return HashID(md5((HashWrapper(site, query, config)).jsonStringify()))
    } catch (e: Exception) {
      throw  RuntimeException(e)
    }
  }
}

data class HashWrapper(val site: String, val query: String, val config: BlastQueryConfig)
{
  val tool    = config.tool
  val dbFiles = config.dbFile
}
