package mblast.lib.query

/**
 * Sequence Character Filter
 *
 * Defines a character predicate that returns `true` or `false` indicating
 * whether a character should be considered when parsing and validating a
 * sequence.
 *
 * This is primarily used to filter out whitespace characters, numeric
 * characters, and any other characters that are ignored by Blast+ tools.
 *
 * The filter should not exclude characters simply on the grounds that they are
 * invalid, filters remove characters from all consideration including
 * validation, meaning filtering out invalid characters will be altering the
 * input sequence in ways that may not be expected by the end user.
 *
 * @author Elizabeth Paige Harper - https://github.com/Foxcapades
 * @since 1.0.0
 */
fun interface SequenceCharFilter {

  /**
   * Predicate method that tests whether the given character should be included
   * in the processing and consideration of a query and its validity.
   *
   * @param ch Character to test.
   *
   * @return `true` if the character should be considered, otherwise `false`.
   */
  fun include(ch: Char): Boolean
}


/**
 * Default Sequence Character Filter
 *
 * Defines a character predicate the implements the most basic required filter
 * functionality.
 *
 * This filter will return `true` for any character that is not a whitespace or
 * decimal digit character, and will return `false` for any whitespace or
 * decimal digit character.
 *
 * @author Elizabeth Paige Harper - https://github.com/Foxcapades
 * @since 1.0.0
 */
class DefaultSequenceCharFilter : SequenceCharFilter {
  override fun include(ch: Char): Boolean {
    return !ch.isWhitespace() && !ch.isDigit()
  }
}