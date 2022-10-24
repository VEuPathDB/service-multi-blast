package mblast.migration.db

import io.foxcapades.lib.jdbc.ro.ReadOnlyResultSet
import java.sql.ResultSet
import java.util.*
import java.util.function.Consumer
import java.util.stream.Stream
import java.util.stream.StreamSupport

/**
 * Returns a stream over an immutable ResultSet where the stream elements are
 * rows in the query result.
 *
 * When the given stream is closed, the underlying ResultSet and Statement will
 * also be closed.
 *
 * @param closeConnectionWithStream Whether the backing connection that created
 * the wrapped ResultSet should also be closed when closing the returned stream.
 *
 * @return A stream over the rows in the wrapped ResultSet.
 */
fun ResultSet.stream(closeConnectionWithStream: Boolean): Stream<ResultSet> {
  return StreamSupport.stream(ResultSetSpliterator(this), false)
    .onClose {
      this.close()
      this.statement.close()

      if (closeConnectionWithStream)
        this.statement.connection.close()
    }
}


private data class ResultSetSpliterator(val raw: ResultSet): Spliterator<ResultSet> {
  private val wrapper = ReadOnlyResultSet(raw)

  override fun tryAdvance(action: Consumer<in ResultSet>): Boolean {
    raw.next() || return false
    action.accept(wrapper)
    return true
  }

  override fun trySplit() = null
  override fun estimateSize() = Long.MAX_VALUE
  override fun characteristics() = Spliterator.ORDERED or Spliterator.IMMUTABLE
}


data class ResultSetIterator<T>(
  val raw: ResultSet,
  val closeConnectionOnClose: Boolean = false,
  val fn: ResultSet.() -> T
)
  : Iterator<T>
  , AutoCloseable
{
  private var current: T? = null
  private var next: T? = null

  fun current(): T {
    if (current == null)
      throw IllegalStateException("next must be called before current can be used")
    return current!!
  }

  override fun hasNext(): Boolean {
    if (raw.next()) {
      next = raw.fn()
      return true
    } else {
      return next != null
    }
  }

  override fun next(): T {
    current = next
    next    = null
    return current!!
  }

  override fun close() {
    raw.close()
    raw.statement.close()
    if (closeConnectionOnClose)
      raw.statement.connection.close()
  }
}