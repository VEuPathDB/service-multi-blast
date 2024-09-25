package mb.lib.query

import mb.api.service.valid.SequenceValidationError
import java.io.InputStream

interface MBlastQuery {
  val sequences: List<MBlastSubQuery>

  fun getFullQuery(): InputStream

  fun validate(): SequenceValidationError?
}
