package mblast.lib.query

/**
 * Sequence Character Validator
 *
 * Defines a character predicate that returns `true` or `false` based on whether
 * the input character is considered valid by the specific validator
 * implementation.
 */
fun interface SequenceCharValidator {
  fun isValid(ch: Char): Boolean
}

/**
 * Validates that a given character or sequence falls within the legal character
 * set for nucleotide sequences.
 *
 * Legal character set:
 * ```
 * ABCD GH K MN RSTUVW Y -
 * abcd gh k mn rstuvw y
 * ```
 */
class NucleotideSequenceCharValidator : SequenceCharValidator {
  override fun isValid(ch: Char) = when (ch) {
    in 'A'..'D', in 'a'..'d' -> true
    in 'G'..'H', in 'g'..'h' -> true
    'K', 'k', 'Y', 'y', '-'  -> true
    in 'M'..'N', in 'm'..'n' -> true
    in 'R'..'W', in 'r'..'w' -> true
    else                     -> false
  }
}

/**
 * Validates that a given character or sequence falls within the legal character
 * set for protein sequences.
 *
 * Legal character set:
 * ```
 * ABCDEFGHI KLMN PQRSTUVWXYZ*-
 * abcdefghi klmn pqrstuvwxyz
 * ```
 */
class ProteinSequenceCharValidator : SequenceCharValidator {
  override fun isValid(ch: Char) = when(ch) {
    in 'A'..'I', in 'a'..'i' -> true
    in 'K'..'N', in 'k'..'n' -> true
    in 'P'..'Z', in 'p'..'z' -> true
    '-', '*'                 -> true
    else                     -> false
  }
}