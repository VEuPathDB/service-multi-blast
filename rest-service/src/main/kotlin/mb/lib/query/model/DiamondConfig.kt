package mb.lib.query.model

import com.fasterxml.jackson.databind.node.ObjectNode
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.jackson.Json

@JvmInline
value class DiamondConfig(val config: DiamondCommandConfig) : JobConfig {
  override fun toJson() = Json.convert(config)
    .let { it as ObjectNode }
    .apply { put("tool", "diamond-" + get("tool")) }
}
