package org.veupathdb.service.mblast.report.valid

import org.veupathdb.service.mblast.report.Const
import org.veupathdb.service.mblast.report.generated.model.ReportJobUserMeta

fun ReportJobUserMeta.validate(errors: ErrorMap) {
  validateSummary(errors)
}

private fun ReportJobUserMeta.validateSummary(errors: ErrorMap) {
  summary?.also {
    if (it.length > Const.MAX_USER_SUMMARY_LENGTH)
      errors.appendError(
        JsonPath.UserMeta.SUMMARY,
        "cannot be longer than ${Const.MAX_USER_SUMMARY_LENGTH} characters in length"
      )
  }
}