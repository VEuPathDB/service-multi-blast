package mblast.util

import java.util.stream.Stream

@JvmInline
value class StreamIterable<T>(val stream: Stream<T>) : Iterable<T> {
  override fun iterator(): Iterator<T> = stream.iterator()
}