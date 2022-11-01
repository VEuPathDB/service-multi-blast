package org.veupathdb.lib.mblast.sdk.util

import com.fasterxml.jackson.databind.JsonNode
import kotlin.reflect.KClass

inline fun JsonNode.dEnumRequireTextual(cls: KClass<*>) =
  dRequireTextual { NonStringEnumException(cls) }

inline fun JsonNode.dRequireTextual(fn: () -> Throwable) {
  if (!isTextual)
    throw fn()
}