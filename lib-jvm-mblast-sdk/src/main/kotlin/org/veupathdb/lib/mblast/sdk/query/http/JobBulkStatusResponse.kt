package org.veupathdb.lib.mblast.sdk.query.http

import org.veupathdb.lib.hash_id.HashID

class JobBulkStatusResponse : HashMap<HashID, JobStatus> {
  constructor() : super()
  constructor(x: Int) : super(x)
}