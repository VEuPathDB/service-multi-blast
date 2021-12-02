@file:JvmName("JSON")

package mb.lib.util;

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule
import java.io.InputStream

val Mapper = jsonMapper {
  addModule(ParameterNamesModule())
  addModule(Jdk8Module())
  addModule(JavaTimeModule())
  addModule(kotlinModule())

  serializationInclusion(JsonInclude.Include.NON_NULL)
}

inline fun jsonObject(init: ObjectNode.() -> Unit = {}): ObjectNode {
  val out = Mapper.createObjectNode()
  init(out)
  return out
}

inline fun jsonArray(init: ArrayNode.() -> Unit = {}): ArrayNode {
  val out = Mapper.createArrayNode()
  init(out)
  return out
}

inline fun <reified T> InputStream.parseJSON() = Mapper.readValue(this, T::class.java)!!

inline fun <reified T> String.parseJSON() = Mapper.readValue(this, T::class.java)!!
inline fun <T> String.toJson(into: T) = Mapper.readerForUpdating(into).readValue<T>(this)!!

inline fun Any?.jsonStringify(): String = Mapper.writeValueAsString(this).toString()
inline fun <reified T> Any.jsonCast(): T = Mapper.convertValue(this, T::class.java)
