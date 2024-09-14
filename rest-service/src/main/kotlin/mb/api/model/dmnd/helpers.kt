package mb.api.model.dmnd

import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.cli.diamond.opts.GeneralDBOptionContainer

internal inline var <T : DiamondCommandConfig> T.databaseFile
  get() = (this as GeneralDBOptionContainer).databaseFile
  set(value) {
    (this as GeneralDBOptionContainer).databaseFile = value
  }
