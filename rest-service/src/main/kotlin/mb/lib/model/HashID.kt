package mb.lib.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.TextNode
import mb.lib.util.hashToString
import mb.lib.util.stringToHash
import java.util.function.Supplier

@Suppress("DataClassPrivateConstructor")
data class HashID private constructor(val string: String, val bytes: ByteArray) {

  @JsonCreator
  constructor(value: String): this(value, stringToHash(value))

  constructor(value: ByteArray): this(hashToString(value), value)

  @JsonValue
  fun toJSON(): JsonNode = TextNode(string)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as HashID

    if (string != other.string) return false

    return true
  }

  override fun hashCode(): Int {
    return string.hashCode()
  }

  companion object {
    fun <T: Throwable> parseOrThrow(value: String, fn: Supplier<T>) = try {
      HashID(value)
    } catch (e: IllegalArgumentException) {
      throw fn.get()
    }
  }
}
