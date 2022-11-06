package org.veupathdb.service.mblast.report.db.sql

import org.intellij.lang.annotations.Language
import org.veupathdb.lib.hash_id.HashID
import org.veupathdb.service.mblast.report.db.*
import java.sql.Connection

// ////////////////////////////////////////////////////////////////////////// //
//                                                                            //
//  Select by ID                                                              //
//                                                                            //
// ////////////////////////////////////////////////////////////////////////// //

@Language("Oracle")
private const val SQL_BY_REPORT_ID = """
SELECT
  ${Column.ReportJobID}
, ${Column.QueryJobID}
, ${Column.Config}
, ${Column.CreatedOn}
FROM
  ${Schema.MBlast}.${Table.ReportConfigs}
WHERE
  ${Column.ReportJobID} = ?
"""

/**
 * Fetches a specific report job by ID.
 *
 * @param reportJobID ID of the job to fetch.
 *
 * @return The target job record, or `null` if it could not be found.
 */
fun Connection.selectReportJob(reportJobID: HashID) =
  prepareStatement(SQL_BY_REPORT_ID).use { ps ->
    ps.setString(1, reportJobID.string)
    ps.executeQuery().use {
      if (it.next())
        it.parseReportDetails()
      else
        null
    }
  }


// ////////////////////////////////////////////////////////////////////////// //
//                                                                            //
//  Select by ID and User                                                     //
//                                                                            //
// ////////////////////////////////////////////////////////////////////////// //

@Language("Oracle")
private const val SQL_BY_REPORT_AND_USER_ID = """
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
  a.${Column.ReportJobID} = ?
  AND b.${Column.UserID} = ?
"""

/**
 * Fetches a specific report by report and user ID.
 *
 * @param reportJobID ID of the job to fetch.
 *
 * @param userID ID of the user that the job must be linked to.
 *
 * @return The target job record, or `null` if it could not be found.
 */
fun Connection.selectReportJob(reportJobID: HashID, userID: Long) =
  prepareStatement(SQL_BY_REPORT_AND_USER_ID).use { ps ->
    ps.setString(1, reportJobID.string)
    ps.setLong(2, userID)
    ps.executeQuery().use {
      if (it.next())
        it.parseUserReportDetails()
      else
        null
    }
  }

