package org.veupathdb.service.mblast.query.model

/**
 * # Full Parent User Query Configuration
 *
 * A [FullUserQueryConfig] instance with the addition of a collection of all the
 * sub job configurations.
 *
 * @author Elizabeth Paige Harper - https://github.com/Foxcapades
 * @since  2.0.0
 */
interface FullParentUserQueryConfig : FullUserQueryConfig, FullParentQueryConfig

data class FullParentUserQueryConfigImpl(
  val queryConfig: BasicQueryConfig,
  val userMeta: QueryUserMeta,
  override val targets: List<BlastTarget>,
  override val childJobs: List<BasicQueryConfig>
)
  : BasicQueryConfig by queryConfig
  , QueryUserMeta by userMeta
  , FullParentUserQueryConfig
{
  constructor(
    userConfig: UserQueryConfig,
    links: List<BlastTarget>,
    childJobs: List<BasicQueryConfig>
  ) : this(
    userConfig,
    userConfig,
    links,
    childJobs
  )
}