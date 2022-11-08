package org.veupathdb.service.mblast.report.db.sql

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.*
import java.sql.Connection
import java.sql.ResultSet


// ////////////////////////////////////////////////////////////////////////// //
//                                                                            //
//  Select by User ID                                                         //
//                                                                            //
// ////////////////////////////////////////////////////////////////////////// //

@Language("Oracle")
private const val SQL_BY_USER = """
SELECT
  a.${Column.ReportJobID}
, a.${Column.QueryJobID}
, a.${Column.Config}
, a.${Column.CreatedOn}
, b.${Column.UserID}
, b.${Column.Summary}
, b.${Column.Description}
FROM
  ${Schema.MBlast}.${Table.ReportConfigs} a
  INNER JOIN ${Schema.MBlast}.${Table.ReportToUsers} b
    ON a.${Column.ReportJobID} = b.${Column.ReportJobID}
WHERE
  b.${Column.UserID} = ?
"""

/**
 * Fetches all report jobs associated with the given [userID].
 *
 * @param userID ID of the user whose report jobs should be fetched.
 *
 * @return A list of zero or more report jobs associated with the target user.
 */
fun Connection.selectReportJobs(userID: Long) =
  prepareStatement(SQL_BY_USER).use { ps ->
    ps.setLong(1, userID)
    ps.executeQuery().use { it.toList(fn = ResultSet::parseUserReportDetails) }
  }


// ////////////////////////////////////////////////////////////////////////// //
//                                                                            //
//  Select by User and Query IDs                                              //
//                                                                            //
// ////////////////////////////////////////////////////////////////////////// //

@Language("Oracle")
private const val SQL_BY_USER_AND_QUERY = """
SELECT
  a.${Column.ReportJobID}
, a.${Column.QueryJobID}
, a.${Column.Config}
, a.${Column.CreatedOn}
, b.${Column.UserID}
, b.${Column.Summary}
, b.${Column.Description}
FROM
  ${Schema.MBlast}.${Table.ReportConfigs} a
  INNER JOIN ${Schema.MBlast}.${Table.ReportToUsers} b
    ON a.${Column.ReportJobID} = b.${Column.ReportJobID}
WHERE
  b.${Column.UserID} = ?
  AND a.${Column.QueryJobID} = ?
"""

/**
 * Fetches all report jobs associated with the target query and user.
 *
 * @param queryJobID ID of the query for which report jobs should be fetched.
 *
 * @param userID ID of the user whose report jobs should be fetched.
 *
 * @return A list of zero or more report jobs associated with the target query
 * and user.
 */
fun Connection.selectReportJobs(queryJobID: HashID, userID: Long) =
  prepareStatement(SQL_BY_USER_AND_QUERY).use { ps ->
    ps.setLong(1, userID)
    ps.setString(2, queryJobID.string)
    ps.executeQuery().use { it.toList(fn = ResultSet::parseUserReportDetails) }
  }
