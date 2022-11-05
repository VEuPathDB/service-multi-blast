package org.veupathdb.lib.mblast.sdk.report.blast

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Blast Formatter Configuration
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data class BlastFormatConfig(
  @JsonProperty("formatType")
  val formatType: BlastOutFormat?,

  @JsonProperty("formatFields")
  val formatFields: List<BlastOutField>?,

  @JsonProperty("showGIs")
  val showGIs: Boolean?,

  @JsonProperty("numDescriptions")
  val numDescriptions: Long?,

  @JsonProperty("numAlignments")
  val numAlignments: Long?,

  @JsonProperty("lineLength")
  val lineLength: Long?,

  @JsonProperty("sortHits")
  val sortHits: BlastHitSorting?,

  @JsonProperty("sortHSPs")
  val sortHSPs: BlastHSPSorting?,

  @JsonProperty("maxTargetSequences")
  val maxTargetSequences: Long?,

  @JsonProperty("parseDefLines")
  val parseDefLines: Boolean?,
)

