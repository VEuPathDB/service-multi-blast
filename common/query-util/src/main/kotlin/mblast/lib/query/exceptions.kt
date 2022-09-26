package mblast.lib.query

sealed class QueryException(
  val lineNumber: Int,
  val charIndex: Int,
  val queryNumber: Int,
  msg: String,
) : Exception(msg)

internal class EmptyQueryException(
  lineNumber: Int,
  queryNumber: Int
) : QueryException(
  lineNumber,
  0,
  queryNumber,
  "Empty sequence for query $queryNumber starting on line $lineNumber"
)

internal class InvalidQueryCharacterException(
  lineNumber: Int,
  charIndex: Int,
  queryNumber: Int,
  char: Char
) : QueryException(
  lineNumber,
  charIndex,
  queryNumber,
  "Invalid character '$char' at position $charIndex on line $lineNumber."
)

internal class QueryTooLongException(
  lineNumber: Int,
  queryNumber: Int,
) : QueryException(
  lineNumber,
  0,
  queryNumber,
  "Query $queryNumber starting on line $lineNumber is empty."
)