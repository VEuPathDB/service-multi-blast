package mblast.query.validate

sealed class MBlastQueryParseException(message: String) : Exception(message)

class EmptySequenceException(val sequenceNumber: Int, val lineNumber: Int)
  : MBlastQueryParseException("Query sequence $sequenceNumber (line $lineNumber) is empty.")

class InvalidSequenceCharacterException(
  val sequenceType: SequenceType,
  val sequenceNumber: Int,
  val lineNumber: Int,
  val colIndex: Int,
  val character: Char,
) : MBlastQueryParseException(
  "Invalid $sequenceType sequence character '$character' in sequence $sequenceNumber at line $lineNumber, pos $colIndex"
)

class TooManySequencesException(val maxSequences: Int)
  : MBlastQueryParseException("The input query contained more than the max allowed number of sequences ($maxSequences)")

class SequenceTooLongException(
  val sequenceType: SequenceType,
  val sequenceNumber: Int,
  val lineNumber: Int,
  val maxSequenceSize: Int,
) : MBlastQueryParseException(
  "Input sequence $sequenceNumber (line $lineNumber) is longer than the max allowed sequence length for $sequenceType queries " +
    "($maxSequenceSize)"
)

class QueryTooLongException(val maxTotalLength: Int)
  : MBlastQueryParseException("The input query was longer than the max allowed query length of $maxTotalLength characters.")

class TooManyDashesOnFirstLineException(val sequenceNumber: Int)
  : MBlastQueryParseException("The first line of input sequence #$sequenceNumber contained too many dashes.")