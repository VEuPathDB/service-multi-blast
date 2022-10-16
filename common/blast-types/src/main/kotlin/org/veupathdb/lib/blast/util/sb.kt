@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.blast.util

/**
 * Appends the given key to the receiver [StringBuilder] only if [isDefault] is
 * `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
) {
  if (!isDefault)
    append(' ').append(key)
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     Boolean,
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value)
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     Byte,
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value.toString())
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     Double,
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value)
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     Int,
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value)
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     Long,
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value)
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     String,
) {
  if (!isDefault)
    append(' ').append(key).append(" '").append(value).append('\'')
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Provider for the argument value to append if [isDefault] is
 * `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     () -> String,
) {
  if (!isDefault)
    append(' ').append(key).append(" '").append(value()).append('\'')
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     UByte
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value.toString())
}


/**
 * Appends the given key/value pair to the receiver [StringBuilder] only if
 * [isDefault] is `false`.
 *
 * @param isDefault Whether the calling type has its default value and thus
 * should not be appended to the receiver.
 *
 * @param key CLI flag to append if [isDefault] is `false`.
 *
 * @param value Argument value to append if [isDefault] is `false`.
 */
internal inline fun StringBuilder.append(
  isDefault: Boolean,
  key:       String,
  value:     UInt
) {
  if (!isDefault)
    append(' ').append(key).append(' ').append(value.toString())
}
