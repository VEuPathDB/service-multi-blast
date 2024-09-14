package mb.lib.query

import mb.api.service.valid.SequenceValidationError

interface MBlastQuery {
  val sequences: List<MBlastSubQuery>

  fun getFullQuery(): String

  fun validate(): SequenceValidationError?
}
