@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.blast.util

import com.fasterxml.jackson.databind.node.ObjectNode

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     Boolean,
) {
  if (!isDefault)
    put(key, value)
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     Byte,
) {
  if (!isDefault)
    put(key, value.toShort())
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     Double,
) {
  if (!isDefault)
    put(key, value)
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     Int,
) {
  if (!isDefault)
    put(key, value)
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     Long,
) {
  if (!isDefault)
    put(key, value)
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     String,
) {
  if (!isDefault)
    put(key, value)
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     UByte,
) {
  if (!isDefault)
    put(key, value.toShort())
}

internal inline fun ObjectNode.put(
  isDefault: Boolean,
  key:       String,
  value:     UInt,
) {
  if (!isDefault)
    put(key, value.toLong())
}
