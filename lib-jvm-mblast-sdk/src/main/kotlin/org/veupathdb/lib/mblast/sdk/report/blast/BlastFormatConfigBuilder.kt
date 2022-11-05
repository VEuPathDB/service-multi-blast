package org.veupathdb.lib.mblast.sdk.report.blast

class BlastFormatConfigBuilder {
  var formatType: BlastOutFormat? = null
  var formatFields: List<BlastOutField>? = null
  var showGIs: Boolean? = null
  var numDescriptions: Long? = null
  var numAlignments: Long? = null
  var lineLength: Long? = null
  var sortHits: BlastHitSorting? = null
  var sortHSPs: BlastHSPSorting? = null
  var maxTargetSequences: Long? = null
  var parseDefLines: Boolean? = null

  fun build() = BlastFormatConfig(
    formatType,
    formatFields,
    showGIs,
    numDescriptions,
    numAlignments,
    lineLength,
    sortHits,
    sortHSPs,
    maxTargetSequences,
    parseDefLines,
  )
}