package mblast.migration.db

import java.sql.ResultSet

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