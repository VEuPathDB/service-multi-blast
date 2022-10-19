package org.veupathdb.service.mblast.query.model

/**
 * # Full Parent Query Config
 *
 * A [FullQueryConfig] implementation that includes a list of sub-jobs.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
sealed interface FullParentQueryConfig : FullQueryConfig, ParentQueryConfig

data class FullParentQueryConfigImpl(
  val queryConfig: BasicQueryConfig,
  override val targets: List<BlastTarget>,
  override val childJobs: List<BasicQueryConfig>
) : BasicQueryConfig by queryConfig, FullParentQueryConfig