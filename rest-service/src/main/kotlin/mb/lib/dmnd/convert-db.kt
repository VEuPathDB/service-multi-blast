package mb.lib.dmnd

import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.cli.diamond.Diamond
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig
import org.veupathdb.lib.jackson.Json

internal fun DiamondCommandConfig(json: ObjectNode): DiamondCommandConfig =
  when (val v = json[JsonKeys.Tool]?.textValue()) {
    DiamondCommand.BlastP.command -> Diamond.blastP(json)
    DiamondCommand.BlastX.command -> Diamond.blastX(json)
    else                          -> throw IllegalArgumentException("invalid diamond tool: \"$v\"")
  }


internal fun DiamondCommandConfig.toHashable(): Any =
  Json.convert(this).also { (it as ObjectNode).remove("outputFormat") }.toString()
