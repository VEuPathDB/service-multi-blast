package mb.lib.query.model

import org.veupathdb.lib.cli.diamond.commands.BlastP
import org.veupathdb.lib.cli.diamond.commands.BlastX
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig

@JvmInline
value class DiamondConfig(val config: DiamondCommandConfig) : JobConfig {
  inline val isBlastP
    get() = config is BlastP

  inline val asBlastP
    get() = config as BlastP

  inline val isBlastX
    get() = config is BlastX

  inline val asBlastX
    get() = config as BlastX
}
