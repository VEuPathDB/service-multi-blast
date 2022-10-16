@file:Suppress("NOTHING_TO_INLINE")

package mblast.query.util

internal const val NUL = '\u0000'
internal const val EOF = '\u0004'

/**
 * Tests if the given character is a standard whitespace character.
 *
 * @param c Character to test.
 *
 * @return `true` if the given value is a whitespace character, otherwise
 * `false`.
 */
internal inline fun isWhitespace(c: Char) = c == ' ' || c == '\t'

/**
 * Tests if the given character is a newline character.
 *
 * @param c Character to test.
 *
 * @return `true` if the given value is a newline character, otherwise `false`.
 */
internal inline fun isNewLine(c: Char) = c == '\n' || c == '\r'

/**
 * Tests if the given character is a base-10 digit character.
 *
 * @param c Character to test.
 *
 * @return `true` if the given value is a digit character, otherwise `false`.
 */
internal inline fun isDigit(c: Char) = c in '0' .. '9'

/**
 * Tests if the given character is one that is ignored by BLAST+ tools.
 *
 * @param c Character to test.
 *
 * @return `true` if the given value is an ignorable character, otherwise
 * `false`.
 */
internal inline fun isIgnorable(c: Char) = isDigit(c) || isWhitespace(c)