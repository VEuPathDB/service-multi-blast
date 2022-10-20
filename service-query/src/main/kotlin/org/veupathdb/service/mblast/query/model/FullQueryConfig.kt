package org.veupathdb.service.mblast.query.model

/**
 * # Full Query Config
 *
 * A full query configuration is a configuration instance that contains enough
 * detail to execute a query job.
 *
 * This differs from the [BasicQueryConfig] instance in that it also includes
 * the BLAST+ query targets which are required in order to execute the BLAST+
 * tool.
 *
 * @author Elizabeth Paige Harper - https://github.com/Foxcapades
 * @since  2.0.0
 */
interface FullQueryConfig : BasicQueryConfig {
  /**
   * The list of query targets for this config.
   */
  val targets: List<BlastTarget>
}

data class FullQueryConfigImpl(val queryConfig: BasicQueryConfig, override val targets: List<BlastTarget>)
  : BasicQueryConfig by queryConfig
  , FullQueryConfig