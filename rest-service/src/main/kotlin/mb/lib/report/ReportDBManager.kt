package mb.lib.report

import mb.lib.db.MBlastDBManager
import mb.lib.model.HashID
import mb.lib.report.db.*
import mb.lib.report.model.ReportRow
import mb.lib.report.model.UserReportRow

class ReportDBManager: MBlastDBManager() {
  fun getReportJob(reportID: HashID) = SelectReportRow(connection, reportID).run()

  fun getUserReportJob(reportID: HashID, userID: Long) = SelectUserReportRow(connection, reportID, userID).run()

  fun getJobReports(jobID: HashID) = SelectAllJobReports(connection, jobID).run()

  fun getUserJobReports(userID: Long) = SelectAllUserReports(connection, userID).run()

  fun getUserJobReports(jobID: HashID, userID: Long) = SelectUserJobReports(connection, jobID, userID).run()

  fun linkUserToReport(reportID: HashID, userID: Long, description: String?) =
    InsertUserReportLink(connection, reportID, userID, description).run()

  fun unlinkUserFromReport(reportID: HashID, userID: Long) = DeleteUserReportLink(connection, reportID, userID).run()

  /**
   * Inserts a report record.
   *
   * @param row Row representing the record to insert.
   */
  fun insertReportRow(row: ReportRow) = InsertReportRow(connection, row).run()

  /**
   * Updates the queue ID and status fields on a single report record.
   *
   * @param row Row containing at least the report ID, the queue ID, and the
   *            status.
   */
  fun updateReportRow(row: ReportRow) = UpdateReportRow(connection, row).run()

  /**
   * Updates the description field on a user-to-report record.
   *
   * @param row Row containing at least the report ID, the user ID, and the new
   *            description value.
   */
  fun updateReportDescription(row: UserReportRow) = UpdateUserReportRow(connection, row).run()

  override fun toString() = "ReportDBManager{}"
}
