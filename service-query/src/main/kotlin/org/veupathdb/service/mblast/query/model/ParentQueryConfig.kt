package org.veupathdb.service.mblast.query.model

sealed interface ParentQueryConfig : BasicQueryConfig {
  /**
   * Child jobs wrapping individual sequences from this parent job.
   *
   * If this parent job contains only a single sequence, this list will be
   * empty.
   *
   * Entries in this list can be safely assumed to have the same user summary,
   * user description, and list of BLAST+ query targets as the parent config.
   */
  val childJobs: List<BasicQueryConfig>
}