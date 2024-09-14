package mb.api.model.dmnd

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import mb.api.model.IOJobConfig
import mb.api.model.io.JsonKeys
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.opts.fields.CompositionBasedStats
import org.veupathdb.lib.cli.diamond.opts.fields.MaskingMode
import org.veupathdb.lib.cli.diamond.opts.fields.Sensitivity
import org.veupathdb.lib.cli.diamond.opts.fields.output_format.OutputFormatOptions

data class IODiamondConfig(
  @get:JsonGetter(JsonKeys.Tool)
  val tool: DiamondCommand,

  @get:JsonGetter(JsonKeys.Query)
  @set:JsonSetter(JsonKeys.Query)
  var query: String?,

  @get:JsonGetter(JsonKeys.ExpectValue)
  @set:JsonSetter(JsonKeys.ExpectValue)
  var eValue: Double?,

  @get:JsonGetter(JsonKeys.MaxTargetSequences)
  @set:JsonSetter(JsonKeys.MaxTargetSequences)
  var maxTargetSeqs: Long?,

  @get:JsonGetter(JsonKeys.Sensitivity)
  @set:JsonSetter(JsonKeys.Sensitivity)
  var sensitivity: Sensitivity?,

  @get:JsonGetter(JsonKeys.Masking)
  @set:JsonSetter(JsonKeys.Masking)
  var masking: MaskingMode?,

  @get:JsonGetter(JsonKeys.CompositionBasedStats)
  @set:JsonSetter(JsonKeys.CompositionBasedStats)
  var compBasedStats: CompositionBasedStats?,

  @get:JsonGetter(JsonKeys.Iterate)
  @set:JsonSetter(JsonKeys.Iterate)
  var iterate: List<Sensitivity>?,

  @get:JsonGetter(JsonKeys.ReportUnaligned)
  @set:JsonSetter(JsonKeys.ReportUnaligned)
  var reportUnaligned: Boolean?,

  @get:JsonGetter(JsonKeys.OutFormat)
  @set:JsonSetter(JsonKeys.OutFormat)
  var outFormat: OutputFormatOptions?,
) : IOJobConfig
