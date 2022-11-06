package org.veupathdb.service.mblast.report.valid

import org.veupathdb.service.mblast.report.generated.model.BlastFormatConfig
import org.veupathdb.service.mblast.report.generated.model.BlastOutFormat

fun BlastFormatConfig.validate(errors: ErrorMap) {
  validateFormatFields(errors)
  validateNumDescriptions(errors)
  validateNumAlignments(errors)
  validateLineLength(errors)
  validateSortHits(errors)
  validateSortHSPs(errors)
  validateMaxTargetSeqs(errors)
}

private fun BlastFormatConfig.validateFormatFields(errors: ErrorMap) {
  if (formatFields.isNullOrEmpty())
    return

  when (formatType) {
    BlastOutFormat.TABULAR             -> {}
    BlastOutFormat.TABULARWITHCOMMENTS -> {}
    BlastOutFormat.CSV                 -> {}
    BlastOutFormat.SAM                 -> {}
    else                               -> {
      errors.appendError(
        JsonPath.BlastConfig.FORMAT_FIELDS,
        "is incompatible with the current ${JsonPath.BlastConfig.FORMAT_TYPE} selection"
      )
      errors.appendError(
        JsonPath.BlastConfig.FORMAT_TYPE,
        "is incompatible with ${JsonPath.BlastConfig.FORMAT_FIELDS}"
      )
    }
  }
}

private fun BlastFormatConfig.validateNumDescriptions(errors: ErrorMap) {
  numDescriptions?.also {
    if (it < 1)
      errors.appendError(JsonPath.BlastConfig.NUM_DESCRIPTIONS, "must be >= 1")

    when (formatType) {
      null                                           -> {}
      BlastOutFormat.PAIRWISE                        -> {}
      BlastOutFormat.QUERYANCHOREDWITHIDENTITIES     -> {}
      BlastOutFormat.QUERYANCHOREDNOIDENTITIES       -> {}
      BlastOutFormat.FLATQUERYANCHOREDWITHIDENTITIES -> {}
      BlastOutFormat.FLATQUERYANCHOREDNOIDENTITIES   -> {}
      else                                           -> {
        errors.appendError(
          JsonPath.BlastConfig.NUM_DESCRIPTIONS,
          "is incompatible with the current ${JsonPath.BlastConfig.FORMAT_TYPE} selection"
        )
        errors.appendError(
          JsonPath.BlastConfig.FORMAT_TYPE,
          "is incompatible with ${JsonPath.BlastConfig.NUM_DESCRIPTIONS}"
        )
      }
    }
  }
}

private fun BlastFormatConfig.validateNumAlignments(errors: ErrorMap) {
  numAlignments?.also {
    if (it < 1)
      errors.appendError(JsonPath.BlastConfig.NUM_ALIGNMENTS, "must be >= 1")
  }
}

private fun BlastFormatConfig.validateLineLength(errors: ErrorMap) {
  lineLength?.also {
    if (it < 1)
      errors.appendError(JsonPath.BlastConfig.LINE_LENGTH, "must be >= 1")

    when (formatType) {
      null                                           -> {}
      BlastOutFormat.PAIRWISE                        -> {}
      BlastOutFormat.QUERYANCHOREDWITHIDENTITIES     -> {}
      BlastOutFormat.QUERYANCHOREDNOIDENTITIES       -> {}
      BlastOutFormat.FLATQUERYANCHOREDWITHIDENTITIES -> {}
      BlastOutFormat.FLATQUERYANCHOREDNOIDENTITIES   -> {}
      else                                           -> {
        errors.appendError(
          JsonPath.BlastConfig.LINE_LENGTH,
          "is incompatible with the current ${JsonPath.BlastConfig.FORMAT_TYPE} selection"
        )
        errors.appendError(
          JsonPath.BlastConfig.FORMAT_TYPE,
          "is incompatible with ${JsonPath.BlastConfig.LINE_LENGTH}"
        )
      }
    }
  }
}

private fun BlastFormatConfig.validateSortHits(errors: ErrorMap) {
  sortHits?.also {
    when (formatType) {
      null                                           -> {}
      BlastOutFormat.PAIRWISE                        -> {}
      BlastOutFormat.QUERYANCHOREDWITHIDENTITIES     -> {}
      BlastOutFormat.QUERYANCHOREDNOIDENTITIES       -> {}
      BlastOutFormat.FLATQUERYANCHOREDWITHIDENTITIES -> {}
      BlastOutFormat.FLATQUERYANCHOREDNOIDENTITIES   -> {}
      else                                           -> {
        errors.appendError(
          JsonPath.BlastConfig.SORT_HITS,
          "is incompatible with the current ${JsonPath.BlastConfig.FORMAT_TYPE} selection"
        )
        errors.appendError(
          JsonPath.BlastConfig.FORMAT_TYPE,
          "is incompatible with ${JsonPath.BlastConfig.SORT_HITS}"
        )
      }
    }
  }
}

private fun BlastFormatConfig.validateSortHSPs(errors: ErrorMap) {
  sortHSPs?.also {
    when (formatType) {
      null                                           -> {}
      BlastOutFormat.PAIRWISE                        -> {}
      else                                           -> {
        errors.appendError(
          JsonPath.BlastConfig.SORT_HSPS,
          "is incompatible with the current ${JsonPath.BlastConfig.FORMAT_TYPE} selection"
        )
        errors.appendError(
          JsonPath.BlastConfig.FORMAT_TYPE,
          "is incompatible with ${JsonPath.BlastConfig.SORT_HSPS}"
        )
      }
    }
  }
}

private fun BlastFormatConfig.validateMaxTargetSeqs(errors: ErrorMap) {
  maxTargetSequences?.also {
    if (it < 1)
      errors.appendError(JsonPath.BlastConfig.MAX_TARGET_SEQS, "must be >= 1")
  }
}