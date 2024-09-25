package mb.lib.query.model

import mb.api.model.dmnd.databaseFile
import mb.lib.dmnd.toHashable
import mb.lib.query.MBlastQuery
import mb.lib.util.jsonStringify
import mb.lib.util.toHashable
import org.veupathdb.lib.blast.BlastQueryConfig
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.hash_id.HashID
import java.io.InputStream

// Could be either a multi-blast, or diamond job.
@Suppress("ArrayInDataClass")
data class MBlastJob(
  val config:      JobConfig,
  val query:       MBlastQuery,
  val site:        String,
  val description: String? = null,
  val userID:      Long,
  val maxDLSize:   Long,
  val targets:     Array<JobTarget>,
) {
  var parentID:    HashID? = null

  var isPrimary:   Boolean = true

  fun digest(query: InputStream): HashID {
    try {
      return HashID.ofMD5(hashWrap(site, HashID.ofMD5(query, true).toString(), config).jsonStringify())
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}

class HashWrapper(
  val site:    String,
  val query:   String,
  val tool:    String,
  val dbFiles: String,
  val config:  Any,
)

private fun hashWrap(site: String, query: String, config: JobConfig) =
  when (config) {
    is BlastConfig -> hashWrap(site, query, config.config as BlastQueryConfig)
    is DiamondConfig -> hashWrap(site, query, config.config)
  }

private fun hashWrap(site: String, query: String, config: BlastQueryConfig) =
  HashWrapper(site, query, config.tool.value, config.dbFile!!, config.toHashable())

private fun hashWrap(site: String, query: String, config: DiamondCommandConfig) =
  HashWrapper(site, query, "diamond-" + config.tool.command, config.databaseFile.toString(), config.toHashable())
