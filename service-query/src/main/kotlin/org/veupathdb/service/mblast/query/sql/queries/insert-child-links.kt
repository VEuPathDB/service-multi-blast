package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.service.mblast.query.sql.util.insertBatchWithRaceCheck
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.QueryToQueries} (
    ${Column.ParentJobID}
  , ${Column.ChildJobID}
  , ${Column.Position}
  )
VALUES
  (?, ?, ?)
"""

/**
 * Inserts links between the given parent job and all the given child jobs in
 * the order they appear in the given `Iterable`.
 *
 * All job IDs that are given to this method (parent and child IDs) must already
 * exist in the database.
 *
 * If a unique constraint violation occurs while attempting to insert this
 * record, then it is most likely a race condition caused by the client/user
 * double submitting the job.  This function will handle that case and return
 * either `true` if the rows were successfully inserted, or `false` if the
 * records already existed.
 *
 * @receiver Connection/transaction the query will be executed on.
 *
 * @param parentJobID Parent job that the child jobs will be linked to.
 *
 * @param childJobIDs An ordered `Iterable` containing the IDs of the jobs to
 * link the parent job to.
 *
 * @return `true` if the links were successfully inserted into the database, or
 * `false` if the records already existed.
 */
fun Connection.insertChildJobLinks(parentJobID: HashID, childJobIDs: Iterable<HashID>) {
  prepareStatement(SQL).use { ps ->
    childJobIDs.forEachIndexed { i, it ->
      ps.setString(1, parentJobID.string)
      ps.setString(2, it.string)
      ps.setInt(3, i)

      ps.addBatch()
      ps.clearParameters()
    }

    ps.insertBatchWithRaceCheck()
  }
}