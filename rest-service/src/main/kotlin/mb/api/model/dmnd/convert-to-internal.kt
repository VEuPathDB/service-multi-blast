package mb.api.model.dmnd

import mb.lib.config.Config
import mb.lib.util.then
import org.veupathdb.lib.cli.diamond.Diamond
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.cli.diamond.opts.*

internal fun IODiamondConfig.toInternal(): DiamondCommandConfig {
  return when(tool) {
    DiamondCommand.BlastP -> Diamond.blastP().fromIO(this)
    DiamondCommand.BlastX -> Diamond.blastX().fromIO(this)
    else -> throw IllegalArgumentException()
  }
}

private fun <T> T.fromIO(config: IODiamondConfig): T
  where
    T : AlignerGeneralOptionContainer
  , T : AlignerSensitivityOptionContainer
  , T : AlignerClusteringOptionContainer
  , T : AlignerClusteringRealignOptionContainer
  , T : AlignerViewOptionContainer
  , T : OutputFormatOptionContainer
  , T : GeneralOptionContainer
{
  config.eValue?.then { expectValue = it }
  config.maxTargetSeqs?.then { maxTargetSeqs = it }
  config.sensitivity?.then { sensitivity = it }
  config.masking?.then { masking = it }
  config.compBasedStats?.then { compBasedStats = it }
  config.iterate?.then { iterate = it }
  config.reportUnaligned?.then { reportUnalignedQueries = it }
  config.outFormat?.then { outputFormat = it }

  threads = Config.diamondCpuLimit

  return this
}
