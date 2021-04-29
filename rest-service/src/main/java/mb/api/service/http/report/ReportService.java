package mb.api.service.http.report;

import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

import mb.api.model.reports.ReportRequest;
import mb.api.model.reports.ReportResponse;
import mb.api.service.http.Util;
import mb.lib.formatter.ReportManager;
import mb.lib.formatter.model.ReportJob;
import mb.lib.formatter.model.UserReportRow;
import mb.lib.model.HashID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.veupathdb.lib.blast.field.FormatType;

public class ReportService
{
  private static final Logger Log = LogManager.getLogger(ReportService.class);

  /**
   * Retrieves a list of all the report jobs created for the given {@code jobID}
   * value.
   * <p>
   * On report job status retrieval, the status will be automatically updated if
   * needed.
   *
   * @return A list of all the report jobs created for the given {@code jobID}.
   */
  public static List<ReportResponse> listReports(HashID jobID, long userID) {
    Log.trace("::listReports(jobID={}, userID={})", jobID, userID);

    try {
      return ReportManager.getUserReportsForJob(jobID, userID)
        .stream()
        .map(ReportService::convert)
        .collect(Collectors.toList());
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  /**
   * Retrieves a list of all the report jobs created by the user identified by
   * {@code userID}.
   *
   * @param userID ID of the user whose report jobs should be retrieved.
   *
   * @return A list of 0 or more report jobs linked to the user identified by
   * {@code userID}.
   */
  public static List<ReportResponse> listAllReports(long userID) {
    Log.trace("::listAllReports(userID={})", userID);

    try {
      return ReportManager.getAllReportsForUser(userID)
        .stream()
        .map(ReportService::convert)
        .collect(Collectors.toList());
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  /**
   * Retrieves a report job identified by {@code reportID} for the user
   * identified by {@code userID}.
   * <p>
   * If the report job exists, but the user was not already linked to it, the
   * user will be linked and the matching job will be returned.
   *
   * @param reportID ID of the report to fetch.
   * @param userID   ID of the user who owns or will be linked to the report.
   *
   * @return The matching report.
   */
  public static ReportResponse getReport(HashID reportID, long userID) {
    Log.trace("::listAllReports(reportID={}, userID={})", reportID, userID);

    try {
      var tmp = ReportManager.getAndLinkReport(reportID, userID)
        .orElseThrow(NotFoundException::new);

      var out = convert(tmp);

      out.getFiles().addAll(ReportManager.getReportFiles(tmp));

      return out;
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  public static ReportResponse submitReport(ReportRequest req, long userID) {
    Log.trace("::submitJob(req={}, userID={})", req, userID);

    if (req.getJobID() == null)
      throw new BadRequestException();

    try {
      return convert(ReportManager.newReportJob(new ReportJob(
        req.getJobID(),
        userID,
        req.toInternalValue(),
        req.getDescription()
      )));
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }


  public static ReportResponse rerunReport(HashID reportID, long userID) {
    Log.trace("::rerunReport(reportID={}, userID={})", reportID, userID);
    try {
      return ReportManager.rerunJob(reportID, userID)
        .map(ReportService::convert)
        .orElseThrow(NotFoundException::new);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }


  public static ReportDownload downloadReport(
    HashID  reportID,
    long    userID,
    String  file,
    boolean download,
    boolean zip
  ) {
    Log.trace("::downloadReport(reportID={}, userID={}, file={}, download={}, zip={})", reportID, userID, file, download, zip);
    try {
      var rep = ReportManager.getAndLinkReport(reportID, userID)
        .orElseThrow(NotFoundException::new);

      var out = ReportManager.getReportFile(rep, file)
        .orElseThrow(NotFoundException::new);

      if (rep.getConfig().getOutFormat() == null)
        return new ReportDownload(FormatType.Pairwise, zip, download, out);
      if (rep.getConfig().getOutFormat().getType() == null)
        return new ReportDownload(FormatType.Pairwise, zip, download, out);

      return new ReportDownload(rep.getConfig().getOutFormat().getType(), zip, download, out);
    } catch (Exception e) {
      throw Util.wrapException(e);
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////

  private static ReportResponse convert(UserReportRow row) {
    return new ReportResponse(
      row.getJobID(),
      row.getReportID(),
      ReportRequest.fromInternalValue(row.getConfig()),
      row.getStatus(),
      row.getDescription()
    );
  }
}
