package mb.api.model.blast.impl

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import mb.api.model.blast.IOBlastConfig
import mb.api.model.blast.IOBlastReportFormat
import mb.lib.blast.model.IOHSPSorting
import mb.lib.blast.model.IOHitSorting
import org.veupathdb.lib.blast.field.*

@Suppress("SpellCheckingInspection")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
sealed class IOBlastConfigImpl(
  override var query: String? = null,
  override var queryLoc: Location? = null,
  override var eValue: String? = null,
  override var outFormat: IOBlastReportFormat? = null,
  override var numDescriptions: Long? = null,
  override var numAlignments: Long? = null,
  override var lineLength: Int? = null,
  override var sortHits: IOHitSorting? = null,
  override var sortHSPs: IOHSPSorting? = null,
  override var lcaseMasking: Boolean? = null,
  override var qCovHSPPerc: Double? = null,
  override var maxHSPs: Long? = null,
  override var maxTargetSeqs: Long? = null,
  override var dbSize: Byte? = null,
  override var searchSpace: Short? = null,
  override var xDropUngap: Double? = null,
  override var parseDefLines: Boolean? = null,
): IOBlastConfig {

  @JsonIgnore
  fun setEValue(value: ExpectValue?) {
    if (value != null)
      eValue = value.value()
  }

  @JsonIgnore
  fun setNumDescriptions(value: NumDescriptions?) {
    if (value != null)
      numDescriptions = value.value()
}

  @JsonIgnore
  fun setNumAlignments(value: NumAlignments?) {
    if (value != null)
      numAlignments = value.value()
  }

  @JsonIgnore
  fun setLineLength(value: LineLength?) {
    if (value != null)
      lineLength = value.value()
  }

  @JsonIgnore
  fun setMaxTargetSeqs(al: MaxTargetSeqs?) {
    if (al != null) {
      maxTargetSeqs = al.value()
    }
  }
}
