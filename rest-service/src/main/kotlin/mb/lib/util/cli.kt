package mb.lib.util

import io.foxcapades.lib.cli.builder.Cli
import io.foxcapades.lib.cli.builder.serial.CliSerializationConfig
import io.foxcapades.lib.cli.builder.serial.IncludeDefault
import org.veupathdb.lib.cli.diamond.commands.DiamondCommandConfig

private val serializer = Cli.newCommandSerializer(CliSerializationConfig {
  longFlagValueSeparator = " "
  shortFlagValueSeparator = " "
  includeDefaultedFlags = IncludeDefault.Never
  includeDefaultedPositionalArgs = IncludeDefault.Always
})

fun DiamondCommandConfig.toBlastServerParams(): List<String> =
  serializer.serializeToSequence(this)
    // omit the first 2 params which will be the word "diamond" and the name of
    // the subcommand which is already known to the server via the "tool" json
    // property.
    .drop(2)
    .toList()
