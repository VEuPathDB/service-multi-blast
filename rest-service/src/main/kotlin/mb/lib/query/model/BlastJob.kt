package mb.lib.query.model

import mb.lib.blast.model.BlastQuery
import mb.lib.util.jsonStringify
import mb.lib.util.md5
import mb.lib.util.toHashable
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.hash_id.HashID

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
      return HashID.ofMD5(HashWrapper(site, query, config).jsonStringify())
    } catch (e: Exception) {
      throw  RuntimeException(e)
    }
  }
}

class HashWrapper(
  val site:   String,
  val query:  String,
  rawConfig: BlastQueryConfig
) {
  val tool    = rawConfig.tool
  val dbFiles = rawConfig.dbFile
  val config  = rawConfig.toHashable()
}
