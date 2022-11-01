package org.veupathdb.lib.mblast.sdk.query.http

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Collections of BLAST database names belonging to a BLAST-able target through
 * the MultiBlast service.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
data class BlastDatabases(
  @JsonProperty("naTargets")
  val naTargets: List<String>,

  @JsonProperty("aaTargets")
  val aaTargets: List<String>,
)