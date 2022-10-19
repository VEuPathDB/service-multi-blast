package org.veupathdb.service.mblast.query.blast.validate

import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.service.mblast.query.generated.model.*


/**
 * Validates the input configuration.
 *
 * If the configuration is valid, this method returns normally.
 *
 * If the configuration is not valid, this method throws an
 * [UnprocessableEntityException] containing all the errors found in the
 * configuration.
 *
 * @receiver Configuration to validate.
 */
fun BlastQueryConfig.validate(errors: MutableMap<String, MutableList<String>>) {
  @Suppress("NAME_SHADOWING")
  val errors = ErrorMap(errors)

  /*
   * Perform validation of fields common to all blast configs
   */

  errors.validateTool(tool)
  errors.validateLocation(queryLocation)
  errors.validateEValue(eValue)
  // softMasking
  // lowercaseMasking
  errors.validateQCovHSPPercent(queryCoverageHSPPercent)
  errors.validateMaxHSPs(maxHSPs)
  errors.validateMaxTargetSeqs(maxTargetSequences)
  // dbSize
  errors.validateSearchSpace(searchSpace)
  // xDropoffUngapped
  errors.validateWindowSize(windowSize)
  // parseDefLines

  when (tool) {
    BlastQueryTool.BLASTN     -> (this as BlastNConfig).validate(errors)
    BlastQueryTool.BLASTP     -> (this as BlastPConfig).validate(errors)
    BlastQueryTool.BLASTX     -> (this as BlastXConfig).validate(errors)
    BlastQueryTool.DELTABLAST -> (this as DeltaBlastConfig).validate(errors)
    BlastQueryTool.PSIBLAST   -> (this as PSIBlastConfig).validate(errors)
    BlastQueryTool.RPSBLAST   -> (this as RPSBlastConfig).validate(errors)
    BlastQueryTool.RPSTBLASTN -> (this as RPSTBlastNConfig).validate(errors)
    BlastQueryTool.TBLASTN    -> (this as TBlastNConfig).validate(errors)
    BlastQueryTool.TBLASTX    -> (this as TBlastXConfig).validate(errors)
    null                      -> {}
    else                -> throw IllegalStateException("Unknown BLAST config type.")
  }

  if (errors.isNotEmpty())
    throw UnprocessableEntityException(errors.getRaw())
}

private fun ErrorMap.validateTool(value: BlastQueryTool?) {
  if (value == null)
    blastField("tool", "is required")
}

/**
 * Validates the query location object.
 */
private fun ErrorMap.validateLocation(value: BlastLocation?) {
  value ?: return

  if (value.start == null)
    blastField("queryLocation.start", "must be set")
  if (value.stop == null)
    blastField("queryLocation.stop", "must be set")

  value.start ?: return
  value.stop  ?: return

  if (value.start < 0)
    blastField("queryLocation.start", "must be >= 0")
  if (value.start >= value.stop)
    blastField("queryLocation.stop", "must be greater than start")
}

/**
 * EValue Regex
 */
private val EValueRegex = Regex("^\\d+(?:\\.\\d+)?(?:[eE][-+]\\d+)?$")

/**
 * Validates the eValue string.
 */
private fun ErrorMap.validateEValue(value: String?) {
  if (!EValueRegex.matches(value ?: return))
    blastField("eValue", "must be a numeric string")
}

/**
 * Validates the qcov_hsp_percent field.
 */
private fun ErrorMap.validateQCovHSPPercent(value: Double?) {
  if ((value ?: return) < 0.0 || value > 100.0)
    blastField("queryCoverageHSPPercent", "must be >= 0 and <= 100")
}

/**
 * Validates the max_hsps field.
 */
private fun ErrorMap.validateMaxHSPs(value: Int?) {
  if ((value ?: return) < 1)
    blastField("maxHSPs", "must be >= 1")
}

private fun ErrorMap.validateMaxTargetSeqs(value: Int?) {
  if ((value ?: return) < 1)
    blastField("maxTargetSequences", "must be >= 1")
}

private fun ErrorMap.validateSearchSpace(value: Byte?) {
  if ((value ?: return) < 0)
    blastField("searchSpace", "must be >= 0")
}

private fun ErrorMap.validateWindowSize(value: Int?) {
  if ((value ?: return) < 0)
    blastField("windowSize", "must be >= 0")
}