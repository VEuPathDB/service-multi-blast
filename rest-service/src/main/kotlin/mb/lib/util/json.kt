@file:JvmName("JSON")

package mb.lib.util

import org.veupathdb.lib.jackson.Json

inline fun Any?.jsonStringify(): String = Json.Mapper.writeValueAsString(this).toString()

inline fun <reified T> Any.jsonCast(): T = Json.Mapper.convertValue(this, T::class.java)
