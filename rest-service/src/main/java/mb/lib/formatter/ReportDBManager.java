package mb.lib.formatter;

import java.util.List;
import java.util.Optional;

import mb.lib.db.MBlastDBManager;
import mb.lib.formatter.db.*;
import mb.lib.formatter.model.ReportRow;
import mb.lib.formatter.model.UserReportRow;
import mb.lib.model.HashID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class ReportDBManager extends MBlastDBManager
{
  private static final Logger Log = LogManager.getLogger(ReportDBManager.class);

  public ReportDBManager() throws Exception {
    Log.trace("::new()");
  }

  public Optional<ReportRow> getReportJob(HashID reportID) throws Exception {
    Log.trace("#getReportRow(reportID={})", reportID);
    return new SelectReportRow(getConnection(), reportID).run();
  }

  public Optional<UserReportRow> getUserReportJob(HashID reportID, long userID) throws Exception {
    Log.trace("#getUserReportRow(reportID={}, userID={})", reportID, userID);
    return new SelectUserReportRow(getConnection(), reportID, userID).run();
  }

  public List<ReportRow> getJobReports(HashID jobID) throws Exception {
    Log.trace("#getJobReports(jobID={})", jobID);
    return new SelectAllJobReports(getConnection(), jobID).run();
  }

  public List<UserReportRow> getUserJobReports(long userID) throws Exception {
    Log.trace("#getUserJobReports(userID={})", userID);
    return new SelectAllUserReports(getConnection(), userID).run();
  }

  public List<UserReportRow> getUserJobReports(HashID jobID, long userID) throws Exception {
    Log.trace("#getUserJobReports(jobID={}, userID={})", jobID, userID);
    return new SelectUserJobReports(getConnection(), jobID, userID).run();
  }

  public void linkUserToReport(HashID reportID, long userID, String description) throws Exception {
    Log.trace("#linkUserToReport(reportID={}, userID={}, description=...)", reportID, userID);
    new InsertUserReportLink(getConnection(), reportID, userID, description).run();
  }

  public void unlinkUserFromReport(HashID reportID, long userID) throws Exception {
    Log.trace("#unlinkUserFromReport(reportID={}, userID={})", reportID, userID);
    new DeleteUserReportLink(getConnection(), reportID, userID).run();
  }

  /**
   * Inserts a new report record.
   *
   * @param row Row representing the record to insert.
   */
  public void insertReportRow(ReportRow row) throws Exception {
    Log.trace("#insertReportRow(row={})", row);
    new InsertReportRow(getConnection(), row).run();
  }

  /**
   * Updates the queue ID and status fields on a single report record.
   *
   * @param row Row containing at least the report ID, the queue ID, and the
   *            status.
   */
  public void updateReportRow(ReportRow row) throws Exception {
    Log.trace("#updateReportRow(row={})", row);
    new UpdateReportRow(getConnection(), row).run();
  }

  /**
   * Updates the description field on a user-to-report record.
   *
   * @param row Row containing at least the report ID, the user ID, and the new
   *            description value.
   */
  public void updateReportDescription(UserReportRow row) throws Exception {
    Log.trace("#updateReportDescription(row={})", row);
    new UpdateUserReportRow(getConnection(), row).run();
  }

  @Override
  public String toString() {
    return "ReportDBManager{}";
  }
}
