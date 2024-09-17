package mb.lib.query.model

import org.veupathdb.lib.blast.BlastConfig
import org.veupathdb.lib.jackson.Json

@JvmInline
value class BlastConfig(val config: BlastConfig) : JobConfig {
  override fun toJson() = Json.convert(config)
}
