package org.veupathdb.lib.mblast.sdk.except

import org.veupathdb.lib.hash_id.HashID

/**
 * Represents an exception thrown because an action was attempted on a job that
 * resulted in the Multi-Blast API returning a 404 error.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class MBlastJobNotFoundException : MBlastJobException {
  constructor(jobID: HashID) : super(jobID)

  constructor(jobID: HashID, message: String) : super(jobID, message)

  constructor(jobID: HashID, cause: Throwable) : super(jobID, cause)

  constructor(jobID: HashID, message: String, cause: Throwable) : super(jobID, message, cause)
}