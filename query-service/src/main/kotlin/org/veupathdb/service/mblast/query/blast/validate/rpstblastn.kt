package org.veupathdb.service.mblast.query.blast.validate

import org.veupathdb.service.mblast.query.generated.model.RPSTBlastNConfig

/**
 * Validates the target IO BLAST configuration to ensure the config is sane
 * against the documented CLI argument rules.
 *
 * @receiver Config to validate
 *
 * @param errors Error map that validation errors will be appended to.
 */
fun RPSTBlastNConfig.validate(errors: ErrorMap) {
  /*
   * The following is a list of all the fields specific to the BlastNConfig type
   * in the order they appear in the JsonPropertyOrder annotation attached to
   * the Impl class.  (Fields up to "parseDefLines" are omitted as they appear
   * in the parent function).
   *
   * Fields that have no special validation appear as comments, fields that do
   * have validation call out to a validation function.
   */

  errors.validateQueryGenCode(queryGenCode)
  // strand
  // compBasedStats
  // seg
  // sumStats
  // xDropoffPrelimGapped
  // xDropoffFinalGapped
  // ungappedOnly
  // useSWTraceback
}