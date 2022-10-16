@file:Suppress("NOTHING_TO_INLINE")

package org.veupathdb.lib.blast.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode


/**
 * Ensures that the target `Double` value is in the given exclusive range.
 *
 * @param k JSON object key for the target `Double` value.
 *
 * @param sE Exclusive start value.
 *
 * @param eE Exclusive end value.
 *
 * @return The target `Double` value.
 *
 * @throws IllegalArgumentException If the target `Double` value is outside the
 * range defined by the given start and end values.
 */
internal inline fun Double.inESet(k: String, sE: Double, eE: Double) =
  if (this > sE && this < eE)
    this
  else
    throw IllegalArgumentException("$k must be an double value between $sE and $eE (exclusive)")


/**
 * Ensures that the target `Int` value is in the given inclusive range.
 *
 * @param k JSON object key for the target `Int` value.
 *
 * @param sI Inclusive start value.
 *
 * @param eI Inclusive end value.
 *
 * @return The target `Int` value.
 *
 * @throws IllegalArgumentException If the target `Int` value is outside the
 * range defined by the given start and end values.
 */
internal inline fun Int.inSet(k: String, sI: Int, eI: Int) =
  if (this !in sI .. eI)
    throw IllegalArgumentException("$k must be an int value between $sI and $eI (inclusive)")
  else
    this


/**
 * Ensures that the target `UByte` value is in the given inclusive range.
 *
 * @param k JSON object key for the target `UByte` value.
 *
 * @param sI Inclusive start value.
 *
 * @param eI Inclusive end value.
 *
 * @return The target `UByte` value.
 *
 * @throws IllegalArgumentException If the target `UByte` value is outside the
 * range defined by the given start and end values.
 */
internal inline fun UByte.inSet(k: String, sI: UByte, eI: UByte) =
  if (this !in sI .. eI)
    throw IllegalArgumentException("$k must be an int value between $sI and $eI (inclusive)")
  else
    this


/**
 * Converts the target [ArrayNode] into a [List] of strings.
 *
 * If any element in the [ArrayNode] is not a string, an exception will be
 * thrown.
 *
 * @param k JSON Key of the target [ArrayNode]
 *
 * @return A new list of strings containing the values from the [ArrayNode].
 */
internal inline fun ArrayNode.toStrList(k: String): List<String> {
  val tmp = ArrayList<String>(size())

  forEach {
    if (!it.isTextual)
      throw IllegalArgumentException("$k must be an array of strings.")

    tmp.add(it.textValue())
  }

  return tmp
}


/**
 * Optionally deserializes a [ArrayNode] value from the target [ObjectNode]
 * using the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optArray(k: String, f: (ArrayNode) -> R): R? =
  this[k]?.reqArray(k)?.let(f)


/**
 * Optionally deserializes a `Boolean` value from the target [ObjectNode] using
 * the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optBool(k: String, f: (Boolean) -> R): R? =
  this[k]?.reqBool(k)?.let(f)


/**
 * Optionally deserializes a `Byte` value from the target [ObjectNode] using the
 * given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optByte(k: String, f: (Byte) -> R): R? =
  this[k]?.reqByte(k)?.let(f)


/**
 * Optionally deserializes a `Double` value from the target [ObjectNode] using
 * the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optDub(k: String, f: (Double) -> R): R? =
  this[k]?.reqDub(k)?.let(f)


/**
 * Optionally deserializes an `Int` value from the target [ObjectNode] using the
 * given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optInt(k: String, f: (Int) -> R): R? =
  this[k]?.reqInt(k)?.let(f)


/**
 * Optionally deserializes an `ObjectNode` value from the target [ObjectNode]
 * using the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optObject(k: String, f: (ObjectNode) -> R): R? =
  this[k]?.reqObject(k)?.let(f)


/**
 * Optionally deserializes a `String` value from the target [ObjectNode] using
 * the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optString(k: String, f: (String) -> R): R? =
  this[k]?.reqString(k)?.let(f)


/**
 * Optionally deserializes a `UByte` value from the target [ObjectNode] using
 * the given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optUByte(k: String, f: (UByte) -> R): R? =
  this[k]?.reqUByte(k)?.let(f)


/**
 * Optionally deserializes a `UInt` value from the target [ObjectNode] using the
 * given mapping function only if the desired property exists.
 *
 * @param k Key to the desired property.
 *
 * @param f Mapping function to be called if the desired property exists on the
 * target [ObjectNode].
 *
 * @param R Type of the mapping function's output.
 *
 * @receiver Target [ObjectNode].
 *
 * @return The mapped value of type [R] if the desired property exists,
 * otherwise `null`.
 */
internal inline fun <R> ObjectNode.optUInt(k: String, f: (UInt) -> R): R? =
  this[k]?.reqUInt(k)?.let(f)


internal inline fun JsonNode.reqArray(k: String): ArrayNode {
  if (!isArray)
    throw IllegalArgumentException("$k must be an array.")

  return this as ArrayNode
}


internal inline fun JsonNode.reqArray(k: () -> String): ArrayNode {
  if (!isArray)
    throw IllegalArgumentException("${k()} must be an array.")

  return this as ArrayNode
}

/**
 * Ensures that the input [JsonNode] is wrapping a boolean value then returns
 * that value.
 *
 * @param key Key used when generating an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqBool(key: String) =
  if (!isBoolean)
    throw IllegalArgumentException("$key must be a boolean value.")
  else
    booleanValue()


/**
 * Ensures that the input [JsonNode] is wrapping a byte value then returns
 * that value.
 *
 * @param key Key used when generating an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqByte(key: String): Byte {
  if (!isIntegralNumber)
    throw IllegalArgumentException("$key must be a byte value.")

  val tmp = shortValue()

  if (tmp > Byte.MAX_VALUE || tmp < Byte.MIN_VALUE)
    throw IllegalArgumentException("$key must be a byte value.")

  return tmp.toByte()
}


/**
 * Ensures that the input [JsonNode] is wrapping a double value then returns
 * that value.
 *
 * @param key Key used when generating an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqDub(key: String) =
  if (!isNumber)
    throw IllegalArgumentException("$key must be a boolean value.")
  else
    doubleValue()


/**
 * Ensures that the input [JsonNode] is wrapping a double value then returns
 * that value.
 *
 * @param k Key provider used to get a key value to generate an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqDub(k: () -> String) =
  if (!isNumber)
    throw IllegalArgumentException("${k()} must be a boolean value.")
  else
    doubleValue()


/**
 * Ensures that the input [JsonNode] is wrapping an int value then returns
 * that value.
 *
 * @param key Key used when generating an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqInt(key: String): Int {
  if (!isIntegralNumber)
    throw IllegalArgumentException("$key must be an int value.")

  return intValue()
}

/**
 * Ensures that the input [JsonNode] is wrapping an int value then returns
 * that value.
 *
 * @param k Key provider used to get a key value to generate an error message.
 *
 * @receiver JsonNode to test.
 *
 * @return The value wrapped by the receiver node.
 */
internal inline fun JsonNode.reqInt(k: () -> String): Int {
  if (!isIntegralNumber)
    throw IllegalArgumentException("${k()} must be an int value.")

  return intValue()
}

internal inline fun JsonNode.reqObject(key: String): ObjectNode {
  if (!isObject)
    throw IllegalArgumentException("$key must be an object.")

  return this as ObjectNode
}

internal inline fun JsonNode.reqString(key: String): String {
  if (!isTextual)
    throw IllegalArgumentException("$key must be a string value.")

  return textValue()
}


internal inline fun JsonNode.reqString(k: () -> String): String {
  if (!isTextual)
    throw IllegalArgumentException("${k()} must be a string value.")

  return textValue()
}

internal inline fun JsonNode.reqUByte(key: String): UByte {
  if (!isIntegralNumber)
    throw IllegalArgumentException("$key must be a uint value.")

  val tmp = longValue()

  if (tmp < 0 || tmp > 255)
    throw IllegalArgumentException("$key must be a uint value.")

  return tmp.toUByte()
}

internal inline fun JsonNode.reqUInt(k: () -> String): UInt {
  if (!isIntegralNumber)
    throw IllegalArgumentException("${k()} must be a uint value.")

  val tmp = longValue()

  if (tmp < 0 || tmp > 42_949_672_95L)
    throw IllegalArgumentException("${k()} must be a uint value.")

  return tmp.toUInt()
}

internal inline fun JsonNode.reqUInt(key: String): UInt {
  if (!isIntegralNumber)
    throw IllegalArgumentException("$key must be a uint value.")

  val tmp = longValue()

  if (tmp < 0 || tmp > 42_949_672_95L)
    throw IllegalArgumentException("$key must be a uint value.")

  return tmp.toUInt()
}
