package mb.query.parse

/**
 * Protein Multi-Sequence Query Parser
 *
 * Parses and validates multi-sequence protein sequence queries.
 *
 * Each sequence in the query must consist of only ignorable characters and
 * characters in the following legal character set:
 * ```
 * ABCDEFGHI KLMN PQRSTUVWXYZ*-
 * abcdefghi klmn pqrstuvwxyz
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
class ProteinQueryParser(maxSequences: Int, maxSequenceSize: Int) : QueryParser(maxSequences, maxSequenceSize) {

  override val sequenceType: String
    get() = "protein"

  override fun isCharValid(char: Char) =
    when (char) {
      in 'A'..'I', in 'a'..'i' -> true
      in 'K'..'N', in 'k'..'n' -> true
      in 'P'..'Z', in 'p'..'z' -> true
      '-', '*'                 -> true
      else                     -> false
    }
}