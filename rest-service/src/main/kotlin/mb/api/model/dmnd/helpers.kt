package mb.api.model.dmnd

import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.cli.diamond.opts.AlignerViewOptionContainer
import org.veupathdb.lib.cli.diamond.opts.GeneralDBOptionContainer

internal inline var <T : DiamondCommandConfig> T.databaseFile
  get() = (this as GeneralDBOptionContainer).databaseFile
  set(value) {
    (this as GeneralDBOptionContainer).databaseFile = value
  }

internal inline var <T : DiamondCommandConfig> T.maxTargetSeqs
  get() = (this as AlignerViewOptionContainer).maxTargetSeqs
  set(value) {
    (this as AlignerViewOptionContainer).maxTargetSeqs = value
  }
