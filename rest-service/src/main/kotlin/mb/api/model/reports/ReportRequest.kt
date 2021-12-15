@file:Suppress("NOTHING_TO_INLINE")

package mb.api.model.reports

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import mb.api.model.blast.IOBlastFormat
import mb.api.model.io.JsonKeys
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import mb.lib.model.HashID
import mb.lib.util.toExternal
import org.veupathdb.lib.blast.BlastFormatter
import org.veupathdb.lib.blast.field.*

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
  @JsonIgnore
  inline fun setNumDescriptions(value: NumDescriptions?) {
    if (value != null) numDescriptions = value.value()
  }

  @JsonIgnore
  fun setNumAlignments(value: NumAlignments?) {
    if (value != null) numAlignments = value.value()
  }

  @JsonIgnore
  fun setLineLength(value: LineLength?) {
    if (value != null) lineLength = value.value()
  }

  @JsonIgnore
  fun setMaxTargetSeqs(value: MaxTargetSeqs?) {
    if (value != null) maxTargetSeqs = value.value()
  }

  @JsonIgnore
  fun toInternal(): BlastFormatter {
    val out = BlastFormatter()
    out.outFormat = OutFormat()
      .setType(FormatType.values()[type!!.ordinal])
      .setDelimiter(delim)
      .setFields(fields)
    out.showGIs = showGIs
    out.setNumDescriptions(numDescriptions)
    out.setNumAlignments(numAlignments)
    out.setLineLength(lineLength)
    out.html = html
    out.sortHits = sortHits?.internalValue
    out.sortHSPs = sortHSPs?.internalValue
    out.setMaxTargetSequences(maxTargetSeqs)
    out.parseDefLines = parseDefLines
    return out
  }
}

inline fun BlastFormatter.toExternal(jobID: HashID): ReportRequest {
  val out = ReportRequest(jobID)
  if (outFormat != null) {
    out.type = IOBlastFormat.values()[outFormat.type.ordinal]
    out.delim = outFormat.delimiter
    if (outFormat.fields != null && outFormat.fields.size > 0) out.fields = outFormat.fields
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
