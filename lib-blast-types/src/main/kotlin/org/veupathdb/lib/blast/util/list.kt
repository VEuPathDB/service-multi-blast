@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.blast.util

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
) {
  if (!isDefault) {
    add(key)
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     Boolean,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     Byte,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     Double,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     Int,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     Long,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     String,
) {
  if (!isDefault) {
    add(key)
    add(value)
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     () -> String,
) {
  if (!isDefault) {
    add(key)
    add(value())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     UByte,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}

internal inline fun MutableList<String>.add(
  isDefault: Boolean,
  key:       String,
  value:     UInt,
) {
  if (!isDefault) {
    add(key)
    add(value.toString())
  }
}
