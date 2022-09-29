package mb.query.parse

/**
 * Nucleotide Multi-Sequence Query Parser
 *
 * Parses and validates multi-sequence nucleotide sequence queries.
 *
 * Each sequence in the query must consist of only ignorable characters and
 * characters in the following legal character set:
 * ```
 * ABCD GH K MN RSTUVW Y -
 * abcd gh k mn rstuvw y
 * ```
 *
 * @author Elizabeth Paige Harper - https://github.com/foxcapades
 * @since v2.0.0
 *
 * @constructor Constructs a new query parser instance.
 *
 * @param maxSequences Maximum number of sequences allowed in a single
 * multi-sequence query.
 *
 * @param maxSequenceSize Maximum number of non-ignorable characters that may
 * appear in a single query sequence (not counting the def line).
 */
class NucleotideQueryParser(maxSequences: Int, maxSequenceSize: Int) : QueryParser(maxSequences, maxSequenceSize) {

  override val sequenceType: String
    get() = "nucleotide"

  override fun isCharValid(char: Char) =
    when (char) {
      in 'A'..'D', in 'a'..'d' -> true
      in 'G'..'H', in 'g'..'h' -> true
      'K', 'k', 'Y', 'y', '-'  -> true
      in 'M'..'N', in 'm'..'n' -> true
      in 'R'..'W', in 'r'..'w' -> true
      else                     -> false
    }
}