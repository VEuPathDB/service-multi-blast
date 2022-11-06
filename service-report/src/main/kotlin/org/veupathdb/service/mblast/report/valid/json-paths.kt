package org.veupathdb.service.mblast.report.valid

object JsonPath {
  const val QUERY_JOB_ID = "queryJobID"
  const val BLAST_CONFIG = "blastConfig"
  const val USER_META    = "userMeta"

  object BlastConfig {
    const val FORMAT_TYPE      = "${BLAST_CONFIG}.formatType"
    const val FORMAT_FIELDS    = "${BLAST_CONFIG}.formatFields"
    const val SHOW_GIS         = "${BLAST_CONFIG}.showGIs"
    const val NUM_DESCRIPTIONS = "${BLAST_CONFIG}.numDescriptions"
    const val NUM_ALIGNMENTS   = "${BLAST_CONFIG}.numAlignments"
    const val LINE_LENGTH      = "${BLAST_CONFIG}.lineLength"
    const val SORT_HITS        = "${BLAST_CONFIG}.sortHits"
    const val SORT_HSPS        = "${BLAST_CONFIG}.sortHSPs"
    const val MAX_TARGET_SEQS  = "${BLAST_CONFIG}.maxTargetSequences"
    const val PARSE_DEF_LINES  = "${BLAST_CONFIG}.parseDefLines"

    fun FORMAT_FIELD(i: Int) = "$FORMAT_FIELDS[$i]"
  }

  object UserMeta {
    const val SUMMARY     = "${USER_META}.summary"
    const val DESCRIPTION = "${USER_META}.description"
  }
}