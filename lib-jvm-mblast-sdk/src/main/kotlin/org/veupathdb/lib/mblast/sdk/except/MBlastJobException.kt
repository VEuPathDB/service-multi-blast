package org.veupathdb.lib.mblast.sdk.except

import org.veupathdb.lib.hash_id.HashID

/**
 * Represents any exception relating to a job or job ID thrown by the
 * Multi-Blast API SDK itself.
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since 1.0.0
 */
open class MBlastJobException : MBlastAPIException {

  /**
   * ID of the job this exception relates to.
   */
  val jobID: HashID

  constructor(jobID: HashID) : super() {
    this.jobID = jobID
  }

  constructor(jobID: HashID, message: String) : super(message) {
    this.jobID = jobID
  }

  constructor(jobID: HashID, cause: Throwable) : super(cause) {
    this.jobID = jobID
  }

  constructor(jobID: HashID, message: String, cause: Throwable) : super(message, cause) {
    this.jobID = jobID
  }
}

