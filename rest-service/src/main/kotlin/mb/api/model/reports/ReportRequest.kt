@file:Suppress("NOTHING_TO_INLINE")

package mb.api.model.reports

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import com.fasterxml.jackson.databind.node.ObjectNode
import mb.api.model.blast.IOBlastFormat
import mb.api.model.io.JsonKeys
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import mb.lib.util.toExternal
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.blast.field.*
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json

@JsonInclude(NON_NULL)
data class ReportRequest(
  @JsonProperty(JsonKeys.JobID)              var jobID:           HashID,
  @JsonProperty(JsonKeys.Description)        var description:     String?            = null,
  @JsonProperty(JsonKeys.Format)             var type:            IOBlastFormat?     = null,
  @JsonProperty(JsonKeys.FieldDelim)         var delim:           String?            = null,
  @JsonProperty(JsonKeys.Fields)             var fields:          List<FormatField>? = null,
  @JsonProperty(JsonKeys.ShowNCBIGIs)        var showGIs:         Boolean?           = null,
  @JsonProperty(JsonKeys.NumDescriptions)    var numDescriptions: Long?              = null,
  @JsonProperty(JsonKeys.NumAlignments)      var numAlignments:   Long?              = null,
  @JsonProperty(JsonKeys.LineLength)         var lineLength:      Int?               = null,
  @JsonProperty(JsonKeys.HTML)               var html:            Boolean?           = null,
  @JsonProperty(JsonKeys.SortHits)           var sortHits:        IOHitSorting?      = null,
  @JsonProperty(JsonKeys.SortHSPs)           var sortHSPs:        IOHSPSorting?      = null,
  @JsonProperty(JsonKeys.MaxTargetSequences) var maxTargetSeqs:   Long?              = null,
  @JsonProperty(JsonKeys.ParseDefLines)      var parseDefLines:   Boolean?           = null,
) {
  inline fun setNumDescriptions(value: NumDescriptions?) {
    if (value != null)
      numDescriptions = value.value
  }

  fun setNumAlignments(value: NumAlignments?) {
    if (value != null)
      numAlignments = value.value
  }

  fun setLineLength(value: LineLength?) {
    if (value != null)
      lineLength = value.value
  }

  fun setMaxTargetSeqs(value: MaxTargetSeqs?) {
    if (value != null)
      maxTargetSeqs = value.value
  }

  fun toInternal(): BlastFormatter {
    val out = BlastFormatter()
    out.outFormat = OutFormat(FormatType.values()[type!!.ordinal], delim, fields?.toMutableList() ?: mutableListOf())
    out.showGIs = showGIs
    out.numDescriptions = numDescriptions?.let(::NumDescriptions)
    out.numAlignments = numAlignments?.let(::NumAlignments)
    out.lineLength = lineLength?.let(::LineLength)
    out.html = html
    out.sortHits = sortHits?.internalValue
    out.sortHSPs = sortHSPs?.internalValue
    out.maxTargetSequences = maxTargetSeqs?.let(::MaxTargetSeqs)
    out.parseDefLines = parseDefLines
    return out
  }

  @JsonValue
  fun toJson() = Json.new<ObjectNode> {
    put(JsonKeys.JobID, jobID.string)

    description?.let { put(JsonKeys.Description, it) }
    type?.let { put(JsonKeys.Format, it.value) }
    delim?.let { put(JsonKeys.FieldDelim, it) }
    fields?.let { putPOJO(JsonKeys.Fields, it) }
    showGIs?.let { put(JsonKeys.ShowNCBIGIs, it) }
    numDescriptions?.let { put(JsonKeys.NumDescriptions, it) }
    numAlignments?.let { put(JsonKeys.NumAlignments, it) }
    lineLength?.let { put(JsonKeys.LineLength, it) }
    html?.let { put(JsonKeys.HTML, it) }
    sortHits?.let { put(JsonKeys.SortHits, it.value) }
    sortHSPs?.let { put(JsonKeys.SortHSPs, it.value) }
    maxTargetSeqs?.let { put(JsonKeys.MaxTargetSequences, it) }
    parseDefLines?.let { put(JsonKeys.ParseDefLines, it) }
  }
}

inline fun BlastFormatter.toExternal(jobID: HashID): ReportRequest {
  val out = ReportRequest(jobID)
  if (outFormat != null) {
    out.type = IOBlastFormat.values()[outFormat!!.type!!.ordinal]
    out.delim = outFormat!!.delimiter
    if (outFormat!!.fields.size > 0)
      out.fields = outFormat!!.fields.toMutableList()
  }
  out.showGIs = showGIs
  out.setNumDescriptions(numDescriptions)
  out.setNumAlignments(numAlignments)
  out.setLineLength(lineLength)
  out.html = html
  out.sortHits = sortHits?.toExternal()
  out.sortHSPs = sortHSPs?.toExternal()
  out.setMaxTargetSeqs(maxTargetSequences)
  out.parseDefLines = parseDefLines
  return out
}
