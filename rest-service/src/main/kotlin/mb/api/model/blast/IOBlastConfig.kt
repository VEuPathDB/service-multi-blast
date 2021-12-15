package mb.api.model.blast

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import mb.api.model.blast.impl.IOBlastConfigImpl
import mb.api.model.io.JsonKeys
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import org.veupathdb.lib.blast.BlastTool
import org.veupathdb.lib.blast.field.Location

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = JsonKeys.Tool)
@JsonSubTypes(JsonSubTypes.Type(
  IOTBlastxConfig::class),
  JsonSubTypes.Type(IOTBlastnConfig::class),
  JsonSubTypes.Type(IOBlastpConfig::class),
  JsonSubTypes.Type(IOBlastxConfig::class),
  JsonSubTypes.Type(IOBlastnConfig::class),
  JsonSubTypes.Type(IOBlastConfig::class))
@JsonDeserialize(`as` = IOBlastConfigImpl::class)
@JsonInclude(NON_DEFAULT)
interface IOBlastConfig {
  @get:JsonProperty(JsonKeys.Tool)
  val tool: BlastTool

  @get:JsonProperty(JsonKeys.Query)
  @set:JsonProperty(JsonKeys.Query)
  var query: String?

  @get:JsonProperty(JsonKeys.QueryLocation)
  @set:JsonProperty(JsonKeys.QueryLocation)
  var queryLoc: Location?

  @get:JsonProperty(JsonKeys.ExpectValue)
  @set:JsonProperty(JsonKeys.ExpectValue)
  var eValue: String?

  @get:JsonProperty(JsonKeys.OutFormat)
  @set:JsonProperty(JsonKeys.OutFormat)
  var outFormat: IOBlastReportFormat?

  @get:JsonProperty(JsonKeys.NumDescriptions)
  @set:JsonProperty(JsonKeys.NumDescriptions)
  var numDescriptions: Long?

  @get:JsonProperty(JsonKeys.NumAlignments)
  @set:JsonProperty(JsonKeys.NumAlignments)
  var numAlignments: Long?

  @get:JsonProperty(JsonKeys.LineLength)
  @set:JsonProperty(JsonKeys.LineLength)
  var lineLength: Int?

  @get:JsonProperty(JsonKeys.SortHits)
  @set:JsonProperty(JsonKeys.SortHits)
  var sortHits: IOHitSorting?

  @get:JsonProperty(JsonKeys.SortHSPs)
  @set:JsonProperty(JsonKeys.SortHSPs)
  var sortHSPs: IOHSPSorting?

  @get:JsonProperty(JsonKeys.LowercaseMasking)
  @set:JsonProperty(JsonKeys.LowercaseMasking)
  var lcaseMasking: Boolean?

  @get:JsonProperty(JsonKeys.QueryCoverageHSPPercent)
  @set:JsonProperty(JsonKeys.QueryCoverageHSPPercent)
  var qCovHSPPerc: Double?

  @get:JsonProperty(JsonKeys.MaxHSPs)
  @set:JsonProperty(JsonKeys.MaxHSPs)
  var maxHSPs: Long?

  @get:JsonProperty(JsonKeys.MaxTargetSequences)
  @set:JsonProperty(JsonKeys.MaxTargetSequences)
  var maxTargetSeqs: Long?

  @get:JsonProperty(JsonKeys.DBSize)
  @set:JsonProperty(JsonKeys.DBSize)
  var dbSize: Byte?

  @get:JsonProperty(JsonKeys.SearchSpace)
  @set:JsonProperty(JsonKeys.SearchSpace)
  var searchSpace: Short?

  @get:JsonProperty(JsonKeys.XDropUngap)
  @set:JsonProperty(JsonKeys.XDropUngap)
  var xDropUngap: Double?

  @get:JsonProperty(JsonKeys.ParseDefLines)
  @set:JsonProperty(JsonKeys.ParseDefLines)
  var parseDefLines: Boolean?
}