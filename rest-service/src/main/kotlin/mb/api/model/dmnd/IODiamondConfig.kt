package mb.api.model.dmnd

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.JsonValue
import mb.api.model.io.JsonKeys
import mb.lib.util.then
import org.veupathdb.lib.cli.diamond.DiamondCommand
import org.veupathdb.lib.cli.diamond.opts.fields.CompositionBasedStats
import org.veupathdb.lib.cli.diamond.opts.fields.MaskingMode
import org.veupathdb.lib.cli.diamond.opts.fields.Sensitivity
import org.veupathdb.lib.cli.diamond.opts.fields.output_format.OutputFormatOptions
import org.veupathdb.lib.jackson.Json

@JsonInclude(JsonInclude.Include.NON_NULL)
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
) {
  @JsonValue
  fun toJson() = Json.newObject {
    put(JsonKeys.Tool, "diamond-" + tool.command)
    eValue?.then { put(JsonKeys.ExpectValue, it) }
    maxTargetSeqs?.then { put(JsonKeys.MaxTargetSequences, it) }
    sensitivity?.then { if (it != Sensitivity.Default) put(JsonKeys.Sensitivity, it.cliValue) }
    compBasedStats?.then { put(JsonKeys.CompositionBasedStats, it.jsonName) }
    iterate?.then { putPOJO(JsonKeys.Iterate, it) }
    reportUnaligned?.then { put(JsonKeys.ReportUnaligned, it) }
    outFormat?.then { putPOJO(JsonKeys.OutFormat, it) }
  }
}
