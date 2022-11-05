package org.veupathdb.lib.mblast.sdk.common.model

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.mblast.sdk.common.model.JobStatus

class JobBulkStatusResponse : HashMap<HashID, JobStatus> {
  constructor() : super()
  constructor(x: Int) : super(x)
}