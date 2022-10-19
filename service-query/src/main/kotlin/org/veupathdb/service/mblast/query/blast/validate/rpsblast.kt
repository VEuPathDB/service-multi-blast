package org.veupathdb.service.mblast.query.blast.validate

import org.veupathdb.service.mblast.query.generated.model.RPSBlastConfig

/**
 * Validates the target IO BLAST configuration to ensure the config is sane
 * against the documented CLI argument rules.
 *
 * @receiver Config to validate
 *
 * @param errors Error map that validation errors will be appended to.
 */
fun RPSBlastConfig.validate(errors: ErrorMap) {
  /*
   * The following is a list of all the fields specific to the BlastNConfig type
   * in the order they appear in the JsonPropertyOrder annotation attached to
   * the Impl class.  (Fields up to "parseDefLines" are omitted as they appear
   * in the parent function).
   *
   * Fields that have no special validation appear as comments, fields that do
   * have validation call out to a validation function.
   */

  // compBasedStats
  // seg
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
  // sumStats
  // xDropoffPrelimGapped
  // xDropoffFinalGapped
  // useSWTraceback
}