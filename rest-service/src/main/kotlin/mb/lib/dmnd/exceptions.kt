package mb.lib.dmnd

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

class QueryTooLongException(val maxTotalLength: Long)
  : MBlastQueryParseException("The input query was longer than the max allowed query length of $maxTotalLength characters.")

class TooManyDashesOnFirstLineException(val sequenceNumber: Int)
  : MBlastQueryParseException("The first line of input sequence #$sequenceNumber contained too many dashes.")

class DNASequenceException()
  : MBlastQueryParseException("This service analyzes protein FASTA input files. Your input file contains only DNA letters (ACGT).")
