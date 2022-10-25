package org.veupathdb.service.mblast.query.sql.queries

import org.veupathdb.lib.blast.Blast
import org.veupathdb.lib.blast.common.BlastQueryBase
import org.veupathdb.service.mblast.query.sql.util.clobToFile
import org.veupathdb.service.mblast.query.sql.util.fetchList
import org.veupathdb.service.mblast.query.sql.util.fetchOpt
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.lib.jackson.Json
import org.veupathdb.service.mblast.query.model.BasicQueryConfig
import org.veupathdb.service.mblast.query.model.BasicQueryConfigImpl
import org.veupathdb.service.mblast.query.model.QueryUserMetaImpl
import org.veupathdb.service.mblast.query.model.UserQueryConfigImpl
import java.sql.Connection
import java.sql.ResultSet

// ---------------------------------------------------------------------------------------------------------------------
//
// -- Select Job By Job ID
//
// ---------------------------------------------------------------------------------------------------------------------

private const val SQL_BY_ID = """
SELECT
  ${Column.QueryJobID}
, ${Column.ProjectID}
, ${Column.Config}
, ${Column.Query}
FROM
  ${Schema.MBlast}.${Table.QueryConfigs}
WHERE
  ${Column.QueryJobID} = ?
"""

/**
 * Retrieves a target BLAST query configuration from the database.
 *
 * @param jobID ID of the job whose query configuration should be fetched.
 *
 * @return Either the query configuration with the given job ID, or null if no
 * matching record exists.
 */
fun Connection.selectQueryConfigByID(jobID: HashID) =
  prepareStatement(SQL_BY_ID).use { ps ->
    ps.setString(1, jobID.string)
    ps.fetchOpt { parseQueryConfig() }
  }


// ---------------------------------------------------------------------------------------------------------------------
//
// -- Select Jobs By User ID
//
// ---------------------------------------------------------------------------------------------------------------------

private const val SQL_BY_USER = """
SELECT
  a.${Column.QueryJobID}
, a.${Column.ProjectID}
, a.${Column.Config} 
, a.${Column.Query}
, b.${Column.UserID}
, b.${Column.Summary}
, b.${Column.Description}
FROM
  ${Schema.MBlast}.${Table.QueryConfigs} a
  INNER JOIN ${Schema.MBlast}.${Table.QueryToUsers} b
    ON a.${Column.QueryJobID} = b.${Column.QueryJobID}
WHERE
  b.${Column.UserID} = ?
"""

/**
 * Retrieves a list of configurations for BLAST queries that are linked to a
 * target user.
 *
 * @param userID ID of the user whose query configs should be fetched.
 *
 * @return A list of zero or more query configurations.
 */
fun Connection.selectQueriesByUser(userID: Long) =
  prepareStatement(SQL_BY_USER).use { ps ->
    ps.setLong(1, userID)
    ps.fetchList { parseUserQueryRecord() }
  }


// ---------------------------------------------------------------------------------------------------------------------
//
// -- Select Jobs By User ID and Job ID
//
// ---------------------------------------------------------------------------------------------------------------------

private const val SQL_BY_ID_AND_USER = """
SELECT
  a.${Column.QueryJobID}
, a.${Column.ProjectID}
, a.${Column.Config} 
, a.${Column.Query}
, b.${Column.UserID}
, b.${Column.Summary}
, b.${Column.Description}
FROM
  ${Schema.MBlast}.${Table.QueryConfigs} a
  INNER JOIN ${Schema.MBlast}.${Table.QueryToUsers} b
    ON a.${Column.QueryJobID} = b.${Column.QueryJobID}
WHERE
  b.${Column.UserID} = ?
  AND a.${Column.QueryJobID} = ?
"""

/**
 * Retrieves a target job that is linked to a target user.
 *
 * If the target job does not exist, or is not linked to the target user, this
 * method returns `null`.
 *
 * @param queryJobID ID of the job whose query config should be fetched.
 *
 * @param userID ID of the user who must be linked to the target job.
 *
 * @return Either the target job query configuration or `null`.
 */
fun Connection.selectQueryConfigByJobAndUser(queryJobID: HashID, userID: Long) =
  prepareStatement(SQL_BY_ID_AND_USER).use { ps ->
    ps.setLong(1, userID)
    ps.setString(2, queryJobID.string)
    ps.fetchOpt { parseUserQueryRecord() }
  }


// ---------------------------------------------------------------------------------------------------------------------
//
// -- Select Jobs By Parent ID
//
// ---------------------------------------------------------------------------------------------------------------------

/**
 * Query to select child blast job configurations by user ID.
 *
 * This query gets the set of parent jobs by user ID from the user links table
 * then uses that set to get the list of blast configurations that are marked as
 * children under the set of parent jobs.
 */
private const val SQL_CHILDREN_BY_USER = """
SELECT
  a.${Column.QueryJobID}
, a.${Column.ProjectID}
, a.${Column.Config}
, a.${Column.Query}
, b.${Column.ParentJobID}
FROM
  ${Schema.MBlast}.${Table.QueryConfigs} a
  INNER JOIN ${Schema.MBlast}.${Table.QueryToQueries} b
    ON a.${Column.QueryJobID} = b.${Column.ChildJobID}
WHERE
  b.${Column.ParentJobID} IN (
    SELECT
      ${Column.QueryJobID}
    FROM
      ${Schema.MBlast}.${Table.QueryToUsers}
    WHERE
      ${Column.UserID} = ?
  )
"""

/**
 * Retrieves a map of job ID to list of child job query configurations attached
 * to a target user.
 *
 * @param userID ID of the user whose total child job set should be retrieved.
 *
 * @return A map of parent job ID to list of child query job configs under that
 * parent job.
 */
fun Connection.selectChildQueriesByUser(userID: Long) =
  prepareStatement(SQL_CHILDREN_BY_USER).use { ps ->
    ps.setLong(1, userID)
    ps.executeQuery().use { it.parseChildConfigMap() }
  }


// ---------------------------------------------------------------------------------------------------------------------
//
// -- Select Jobs By Parent ID
//
// ---------------------------------------------------------------------------------------------------------------------

private const val SQL_CHILDREN_BY_PARENT = """
SELECT
  ${Column.QueryJobID}
, ${Column.ProjectID}
, ${Column.Config}
, ${Column.Query}
FROM
  ${Schema.MBlast}.${Table.QueryConfigs}
WHERE
  ${Column.QueryJobID} IN (
    SELECT
      ${Column.ChildJobID}
    FROM
      ${Schema.MBlast}.${Table.QueryToQueries}
    WHERE
      ${Column.ParentJobID} = ?
  )
"""

/**
 * Retrieves a list of child job query configurations for a target parent job.
 *
 * If no job exists with the given job ID, an empty list will be returned.
 *
 * @param queryJobID ID of the target parent job whose child jobs should be
 * fetched.
 *
 * @return A list of zero or more child jobs linked to the given parent job ID.
 */
fun Connection.selectChildQueriesByParent(queryJobID: HashID) =
  prepareStatement(SQL_CHILDREN_BY_PARENT).use { ps ->
    ps.setString(1, queryJobID.string)
    ps.fetchList { parseQueryConfig() }
  }

// ---------------------------------------------------------------------------------------------------------------------
//
// -- Result Parsing
//
// ---------------------------------------------------------------------------------------------------------------------

/**
 * Parses a [BasicQueryConfig] instance from the receiver [ResultSet].
 *
 * This method requires that the `ResultSet` cursor is already on a row that can
 * be parsed.
 *
 * @receiver ResultSet from which a row will be parsed.
 *
 * @return A parsed `BasicQueryConfig` instance.
 */
private fun ResultSet.parseQueryConfig(): BasicQueryConfig =
  BasicQueryConfigImpl(
    HashID(getString(Column.QueryJobID)),
    getString(Column.ProjectID),
    Blast.of(Json.parse(getString(Column.Config))) as BlastQueryBase,
    clobToFile(Column.Query),
  )

private fun ResultSet.parseUserQueryRecord() =
  UserQueryConfigImpl(
    parseQueryConfig(),
    QueryUserMetaImpl(
      getLong(Column.UserID),
      getString(Column.Summary),
      getString(Column.Description)
    )
  )

private fun ResultSet.parseChildConfigMap(): Map<HashID, List<BasicQueryConfig>> =
  HashMap<HashID, MutableList<BasicQueryConfig>>(8).also {
    while (next())
      it.computeIfAbsent(HashID(getString(Column.ParentJobID))) { ArrayList(8) }
        .add(parseQueryConfig())
  }
