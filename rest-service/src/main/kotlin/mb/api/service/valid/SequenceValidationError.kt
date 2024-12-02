package mb.api.service.valid

import mb.lib.util.joinToFriendlyString

sealed interface SequenceValidationError {
  val message: String
}

class SequenceMissingDefLineValidationError(val sequence: Int) : SequenceValidationError {
  override val message
    get() = "Sequence $sequence had no defline"
}

class SequenceDefLineValidationError(val badDefLines: List<Int>) : SequenceValidationError, RuntimeException() {
  override val message
    get() =
      if (badDefLines.size == 1)
        "No sequence id found in the defline on line ${badDefLines[0]}"
      else
        "No sequence id found in the deflines on lines ${badDefLines.joinToFriendlyString()}"
}

class SequenceCharacterValidationError(
  val sequence:  Int,
  val line:      Int,
  val position:  Int,
  val character: Char
) : SequenceValidationError {
  override val message
    get() = "Invalid character '$character' in sequence $sequence at line $line, position $position."
}

class SequenceLengthValidationError(
  val length:    Int,
  val maxLength: Int
) : SequenceValidationError {
  override val message
    get() = "Sequence too long, max length is $maxLength input length was $length."
}

class SequenceEmptyValidationError : SequenceValidationError {
  override val message: String
    get() = "Empty query.  Query must have 1 or more sequences."
}

class SequenceDashesValidationError(val sequence: Int) : SequenceValidationError {
  override val message: String
    get() = "The first line of input sequence #$sequence contained too many dashes."
}
