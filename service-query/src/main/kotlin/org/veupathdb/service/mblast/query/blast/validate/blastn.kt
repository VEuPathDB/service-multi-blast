package org.veupathdb.service.mblast.query.blast.validate

import org.veupathdb.service.mblast.query.generated.model.BlastNConfig

/**
 * Validates the target IO BLAST configuration to ensure the config is sane
 * against the documented CLI argument rules.
 *
 * @receiver Config to validate
 *
 * @param errors Error map that validation errors will be appended to.
 */
fun BlastNConfig.validate(errors: ErrorMap) {

  /*
   * The following is a list of all the fields specific to the BlastNConfig type
   * in the order they appear in the JsonPropertyOrder annotation attached to
   * the Impl class.  (Fields up to "parseDefLines" are omitted as they appear
   * in the parent function).
   *
   * Fields that have no special validation appear as comments, fields that do
   * have validation call out to a validation function.
   */

  // strand
  // task
  errors.validateWordSize(4, wordSize)
  // gapOpen
  // gapExtend
  errors.validatePenalty(penalty)
  errors.validateReward(reward)
  // useIndex
  // indexName
  // dust
  errors.validateDBSoftHardMask(
    dbSoftMask,
    dbHardMask
  )
  errors.validatePercentIdentity(percentIdentity)
  errors.validateCullingLimit(cullingLimit)
  if (cullingLimit != null) {
    if (bestHitOverhang != null) {
      errors.blastField("cullingLimit", "incompatible with bestHitOverhang")
      errors.blastField("bestHitOverhang", "incompatible with cullingLimit")
    }
    if (bestHitScoreEdge != null) {
      errors.blastField("cullingLimit", "incompatible with bestHitScoreEdge")
      errors.blastField("bestHitScoreEdge", "incompatible with cullingLimit")
    }
  }
  // subjectBestHit
  // templateType
  errors.validateTemplateLength(templateLength)
  // sumStats
  // xDropoffPrelimGapped
  // xDropoffFinalGapped
  // nonGreedy
  // minRawGappedScore
  // ungappedOnly
  errors.validateOffDiagonalRange(offDiagonalRange)
}

private fun ErrorMap.validatePenalty(value: Int?) {
  if ((value ?: return) > 0)
    blastField("penalty", "must be <= 0")
}

private fun ErrorMap.validateReward(value: Int?) {
  if ((value ?: return) < 0)
    blastField("reward", "must be >= 0")
}

private fun ErrorMap.validateTemplateLength(value: Int?) {
  when (value ?: return) {
    16, 18, 21 -> return
    else       -> blastField("templateLength", "must be one of 16, 18, or 21")
  }
}

private fun ErrorMap.validateOffDiagonalRange(value: Int?) {
  if ((value ?: return) < 0)
    blastField("offDiagonalRange", "must be >= 0")
}