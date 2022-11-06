package org.veupathdb.service.mblast.report.valid

import org.veupathdb.lib.container.jaxrs.errors.UnprocessableEntityException
import org.veupathdb.service.mblast.report.generated.model.ReportJobPostRequest

fun ReportJobPostRequest.validate() {
  val errors = NewErrorMap()

  validateQueryJobID(errors)
  validateBlastConfig(errors)
  validateUserMeta(errors)

  if (errors.isNotEmpty())
    throw UnprocessableEntityException(errors)
}

private fun ReportJobPostRequest.validateQueryJobID(errors: ErrorMap) {
  if (queryJobID == null)
    errors.appendError(JsonPath.QUERY_JOB_ID, "is required")
}

private fun ReportJobPostRequest.validateBlastConfig(errors: ErrorMap) =
  blastConfig?.validate(errors)

private fun ReportJobPostRequest.validateUserMeta(errors: ErrorMap) =
  userMeta?.validate(errors)