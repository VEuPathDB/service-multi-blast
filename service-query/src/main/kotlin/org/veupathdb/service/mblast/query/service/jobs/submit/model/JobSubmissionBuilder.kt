package org.veupathdb.service.mblast.query.service.jobs.submit.model

import mblast.query.pipe.SequenceFileWriteResult
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.model.QueryUserMeta

/**
 * Intermediate type between a raw job submission and the `JobSubmission` type.
 */
data class JobSubmissionBuilder(
  var parentJobID:     HashID? = null,
  var blastConfigWDB:  BlastQueryBase? = null,
  var blastConfigWODB: BlastQueryBase? = null,
  var blastTargets:    List<BlastTarget>? = null,
  var projectID:       String? = null,
  var queryFiles:      SequenceFileWriteResult? = null,
  var addToUserJobs:   Boolean? = null,
  var userMeta:        QueryUserMeta? = null
)