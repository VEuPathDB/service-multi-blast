package org.veupathdb.service.mblast.query.model

/**
 * # Query Job User Metadata
 *
 * User metadata attached to a BLAST query job.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since  2.0.0
 */
interface QueryUserMeta {

  /**
   * ID of the user attached to the query job.
   */
  val userID:      Long

  /**
   * Optional short summary provided by the user for the query job.
   */
  val summary:     String?

  /**
   * Optional long description provided by the user for the query job.
   */
  val description: String?
}

data class QueryUserMetaImpl(
  override val userID:      Long,
  override val summary:     String?,
  override val description: String?
) : QueryUserMeta