package org.veupathdb.service.mblast.query.sql.util

import org.veupathdb.service.mblast.query.sql.queries.ORA_CODE_UNIQUE_VIOLATION
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

/**
 * Executes the receiver statement and handles unique constraint violations.
 *
 * If the statement execution completes without exception, this method will
 * return `true` indicating that the record was inserted successfully.
 *
 * If the statement execution throws a unique constraint violation error, this
 * method will return `false` indicating that the record was not inserted as it
 * already existed.
 *
 * If the statement execution throws a different exception, that exception will
 * be rethrown.
 *
 * @receiver PreparedStatement that will be executed.
 *
 * @return `true` if the row was inserted successfully, `false` if the target
 * record already existed.
 */
fun PreparedStatement.insertWithRaceCheck() =
  try {
    execute()
    true
  } catch (e: SQLException) {
    if (e.errorCode == ORA_CODE_UNIQUE_VIOLATION)
      false
    else
      throw e
  }

/**
 * Executes the receiver statement and handles unique constraint violations.
 *
 * If the statement execution completes without exception, this method will
 * return `true` indicating that the record was inserted successfully.
 *
 * If the statement execution throws a unique constraint violation error, this
 * method will return `false` indicating that the record was not inserted as it
 * already existed.
 *
 * If the statement execution throws a different exception, that exception will
 * be rethrown.
 *
 * @receiver PreparedStatement that will be executed.
 *
 * @return `true` if the row was inserted successfully, `false` if the target
 * record already existed.
 */
fun PreparedStatement.insertBatchWithRaceCheck() =
  try {
    executeBatch()
    true
  } catch (e: SQLException) {
    if (e.errorCode == ORA_CODE_UNIQUE_VIOLATION)
      false
    else
      throw e
  }

/**
 * Executes the target query and fetches an optional return value based on
 * whether the query had a result row.
 *
 * @receiver PreparedStatement instance that is ready to be executed.
 *
 * @param fn Mapping function that will be called only if the query succeeds and
 * there is a row available in the query [ResultSet].
 *
 * @param T Type of the value that may be returned by the given function.
 *
 * @return Either an instance of [T] if there was a row available, or `null` if
 * there were no rows available.
 */
inline fun <T> PreparedStatement.fetchOpt(fn: ResultSet.() -> T): T? =
  executeQuery().use { it.opt(fn) }

/**
 * Executes the target query and fetches a list of zero or more values parsed
 * from the rows in the query result.
 *
 * @R
 */
inline fun <T> PreparedStatement.fetchList(fn: ResultSet.() -> T): List<T> =
  executeQuery().use { it.map(fn = fn) }
