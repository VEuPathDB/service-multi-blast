package org.veupathdb.service.mblast.query.service.jobs.submit.model

import org.veupathdb.lib.blast.common.BlastQueryBase

/**
 * A pair of BLAST query configurations.
 *
 * One headed for the job queue and job workspace, the other headed for the
 * database.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since v2.0.0
 */
data class BlastConfigPair(
  val forQueue: BlastQueryBase,
  val forDB: BlastQueryBase
)