package mb.api.model.dmnd

import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.cli.diamond.opts.*
import java.nio.file.Path

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

internal inline var <T : DiamondCommandConfig> T.quiet
  get() = (this as GeneralOptionContainer).quiet
  set(value) {
    (this as GeneralOptionContainer).quiet = value
  }

internal inline var <T : DiamondCommandConfig> T.outputFile
  get() = (this as GeneralOutputOptionContainer).outputFile
  set(value) {
    (this as GeneralOutputOptionContainer).outputFile = value
  }

internal inline var <T : DiamondCommandConfig> T.query: Path?
  get() = (this as AlignerGeneralOptionContainer).query?.get(0)
  set(value) {
    (this as AlignerGeneralOptionContainer).query = value?.let(::listOf)
  }
