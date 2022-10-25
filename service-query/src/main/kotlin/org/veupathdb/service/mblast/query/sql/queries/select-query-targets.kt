package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.query.model.BlastTarget
import java.sql.Connection
import java.sql.ResultSet

private const val SQL_SINGLE = """
SELECT
  ${Column.QueryJobID}
, ${Column.TargetName}
, ${Column.TargetFile}
FROM
  ${Schema.MBlast}.${Table.QueryToTargets}
WHERE
  ${Column.QueryJobID} = ?
"""

private const val SQL_BULK = """
SELECT
  ${Column.QueryJobID}
, ${Column.TargetName}
, ${Column.TargetFile}
FROM
  ${Schema.MBlast}.${Table.QueryToTargets}
WHERE
  ${Column.QueryJobID} IN (
    SELECT
      ${Column.QueryJobID}
    FROM
      ${Schema.MBlast}.${Table.QueryToUsers}
    WHERE
      ${Column.UserID} = ?
  )
"""

/**
 * Retrieves the list of BLAST targets attached to the target job record.
 *
 * @param jobID ID of the query job whose BLAST targets should be fetched.
 *
 * @return A list of BLAST targets for the target job.
 */
fun Connection.selectQueryTargetsByJob(jobID: HashID) =
  prepareStatement(SQL_SINGLE).use { ps ->
    ps.setString(1, jobID.string)
    ps.executeQuery().use { it.parseQueryTargets() }
  }

/**
 * Retrieves a map of job IDs to the lists of BLAST targets attached to the jobs
 * with those IDs for a target user.
 *
 * @param userID ID of the user whose BLAST targets should be fetched.
 *
 * @return A map of job ID to list of BLAST targets for the job with that ID.
 */
fun Connection.selectQueryTargetsByUser(userID: Long) =
  prepareStatement(SQL_BULK).use { ps ->
    ps.setLong(1, userID)
    ps.executeQuery().use { it.parseQueryTargetMap() }
  }

private fun ResultSet.parseQueryTargetMap(): Map<HashID, List<BlastTarget>> =
  HashMap<HashID, ArrayList<BlastTarget>>().also {
    while (next())
      it.computeIfAbsent(HashID(getString(Column.QueryJobID))) { ArrayList(8) }
        .add(parseQueryTarget())
  }

private fun ResultSet.parseQueryTargets(): List<BlastTarget> =
  ArrayList<BlastTarget>(8).also {
    while (next())
      it.add(parseQueryTarget())
  }

private fun ResultSet.parseQueryTarget() =
  BlastTarget(getString(Column.TargetName), getString(Column.TargetFile))