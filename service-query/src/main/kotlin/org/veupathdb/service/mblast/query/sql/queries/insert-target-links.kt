package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.service.mblast.query.model.BlastTarget
import org.veupathdb.service.mblast.query.sql.util.insertBatchWithRaceCheck
import org.veupathdb.lib.hash_id.HashID
import java.sql.Connection

private const val SQL = """
INSERT INTO
  ${Schema.MBlast}.${Table.QueryToTargets} (
    ${Column.QueryJobID}
  , ${Column.TargetName}
  , ${Column.TargetFile}
  )
VALUES
  (?, ?, ?)
"""

/**
 * Inserts a list of BLAST query targets into the database, linked to a target
 * job.
 *
 * If a unique constraint violation occurs while attempting to insert this
 * record, then it is most likely a race condition caused by the client/user
 * double submitting the job.  This function will handle that case and return
 * either `true` if the row was successfully inserted, or `false` if the record
 * already existed.
 *
 * @param jobID ID of the target query job the targets will be linked to.
 *
 * @param targets Targets to be inserted.
 *
 * @return `true` if the record was successfully inserted. `false` if the record
 * already existed.
 */
fun Connection.insertJobTargetLinks(jobID: HashID, targets: Iterable<BlastTarget>) =
  prepareStatement(SQL).use { ps ->
    targets.forEach {
      ps.setString(1, jobID.string)
      ps.setString(2, it.displayName)
      ps.setString(3, it.fileName)

      ps.addBatch()
      ps.clearParameters()
    }

    ps.insertBatchWithRaceCheck()
  }