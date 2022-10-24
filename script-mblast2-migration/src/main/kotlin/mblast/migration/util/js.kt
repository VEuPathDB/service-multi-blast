package mblast.migration.util

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import org.veupathdb.lib.jackson.Json
import java.math.BigDecimal
import java.math.BigInteger
import java.util.function.BiConsumer
import java.util.function.BinaryOperator
import java.util.function.Function
import java.util.function.Supplier
import java.util.stream.Collector

class JsonArrayCollector<T> : Collector<T, MutableList<T>, ArrayNode> {

  override fun supplier(): Supplier<MutableList<T>> = Supplier { ArrayList() }

  override fun accumulator(): BiConsumer<MutableList<T>, T> = BiConsumer { l, v -> l.add(v) }

  override fun combiner(): BinaryOperator<MutableList<T>> = BinaryOperator { a, b -> a.addAll(b); a }

  override fun finisher(): Function<MutableList<T>, ArrayNode> = Function {
    Json.newArray(it.size) { it.forEach { add(it as Any) } }
  }

  override fun characteristics(): Set<Collector.Characteristics> {
    return setOf()
  }
}

fun ArrayNode.add(any: Any) {
  when (any) {
    is String     -> add(any)
    is Int        -> add(any)
    is Double     -> add(any)
    is Boolean    -> add(any)
    is JsonNode   -> add(any)
    is Long       -> add(any)
    is Byte       -> add(any.toInt())
    is Short      -> add(any.toInt())
    is BigDecimal -> add(any)
    is BigInteger -> add(any)
    is Float      -> add(any)
    else          -> add(Json.convert(any))
  }
}
