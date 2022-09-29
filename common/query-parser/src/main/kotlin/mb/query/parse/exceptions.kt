package mb.query.parse

sealed class MBlastQueryParseException(message: String) : Exception(message)

class EmptySequenceException(val sequenceNumber: Int, val lineNumber: Int)
  : MBlastQueryParseException("Query sequence $sequenceNumber (line $lineNumber) is empty.")

class InvalidSequenceCharacterException(
  val sequenceType: String,
  val sequenceNumber: Int,
  val lineNumber: Int,
  val colIndex: Int,
  val character: Char,
) : MBlastQueryParseException(
  "Invalid $sequenceType sequence character '$character' in sequence $sequenceNumber at line $lineNumber, pos $colIndex"
)

class TooManySequencesException(
  val maxSequences: Int
) : MBlastQueryParseException("The input query contained more than the max allowed number of sequences ($maxSequences)")

class SequenceTooLongException(
  val sequenceType: String,
  val sequenceNumber: Int,
  val maxSequenceSize: Int,
) : MBlastQueryParseException(
  "Input sequence number $sequenceNumber is longer than the max allowed sequence length for $sequenceType queries " +
    "($maxSequenceSize)"
)