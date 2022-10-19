package org.veupathdb.service.mblast.query.sql.util

import org.veupathdb.lib.mblast.utils.tmp.TempFiles
import java.sql.ResultSet


/**
 * Parse optional value.
 *
 * If the `ResultSet` contains at least one row, this mixin function will index
 * the `ResultSet` cursor to that row, call the given parsing function and
 * pass back up the value returned by that function.
 *
 * If the `ResultSet` contains zero rows, this mixin function will simply return
 * `null`.
 *
 * @receiver ResultSet on which the given parsing function will be called if
 * there is at least one row present.
 *
 * @param fn Parsing function that will be called one time if and only if there
 * is at least one row present in the receiver `ResultSet`.
 *
 * @param T Type of the value that will be returned by the given parsing
 * function.
 *
 * @return The parsed value returned by [fn] if and only if there is at least
 * one row available in the receiver `ResultSet`.  `null` if there are zero rows
 * available in the receiver `ResultSet`.
 */
inline fun <T> ResultSet.opt(fn: ResultSet.() -> T) =
  if (next())
    fn()
  else
    null

/**
 * Map rows to values.
 *
 * Calls the given mapping function on each row available in the receiver
 * `ResultSet` and collects the results in a list which is returned.
 *
 * If the receiver `ResultSet` does not have any rows available, the returned
 * list will be empty.
 *
 * @receiver ResultSet on which the given parsing function will be called for
 * each available result row.
 *
 * @param preSize Initial size of the list into which the parsed rows will be
 * added.
 *
 * @param fn Parsing function that will be called for each row available in the
 * `ResultSet`.
 *
 * @param T Type of the values that will be returned by the given parsing
 * function.
 *
 * @return A list of values parsed from the receiver `ResultSet`.
 */
inline fun <T> ResultSet.map(preSize: Int = 8, fn: ResultSet.() -> T): List<T> =
  ArrayList<T>(preSize).also {
    while (next()) {
      it.add(fn())
    }
  }

/**
 * Writes the CLOB value at the give column index out to a temp file then
 * returns a handle on that file.
 *
 * **WARNING**: This method makes no attempt to verify whether the target CLOB
 * column is `null`, and assumes that the value is not `null`.
 *
 * @param colLabel Column name of the CLOB data to write out to a temp file.
 *
 * @return A handle on a temp file containing the value read from the CLOB
 * column at the given column index.
 */
fun ResultSet.clobToFile(colLabel: String) =
  getClob(colLabel).characterStream.use { res ->
    TempFiles.create().also {
      it.bufferedWriter().use { wri ->
        res.transferTo(wri)
        wri.flush()
      }
    }
  }
