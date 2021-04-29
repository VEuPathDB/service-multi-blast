package mb.api.controller;

import java.util.List;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import mb.api.controller.resources.Reports;
import mb.api.model.reports.ReportRequest;
import mb.api.model.reports.ReportResponse;
import mb.api.service.http.report.ReportService;
import mb.lib.model.HashID;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;

public class ReportController implements Reports
{
  private final Request request;

  public ReportController(@Context Request request) {
    this.request = request;
  }

  @Override
  public List<ReportResponse> getAllReports(String jobID) {
    var userID = UserProvider.lookupUser(request)
      .orElseThrow(Utils::noUserExcept)
      .getUserID();

    if (jobID != null)
      return ReportService.listReports(HashID.parseOrThrow(jobID, NotFoundException::new), userID);

    return ReportService.listAllReports(userID);
  }

  @Override
  public ReportResponse getReport(String reportID) {
    var userID = UserProvider.lookupUser(request)
      .orElseThrow(Utils::noUserExcept)
      .getUserID();

    return ReportService.getReport(HashID.parseOrThrow(reportID, NotFoundException::new), userID);
  }

  @Override
  public ReportResponse newReport(ReportRequest req) {
    var userID = UserProvider.lookupUser(request)
      .orElseThrow(Utils::noUserExcept)
      .getUserID();

    return ReportService.submitReport(req, userID);
  }

  @Override
  public ReportResponse rerunReport(String reportID) {
    var userID = UserProvider.lookupUser(request)
      .orElseThrow(Utils::noUserExcept)
      .getUserID();

    return ReportService.rerunReport(HashID.parseOrThrow(reportID, NotFoundException::new), userID);
  }

  @Override
  public Response getReportData(String reportID, String fileName, boolean download, boolean zip) {
    var userID = UserProvider.lookupUser(request)
      .orElseThrow(Utils::noUserExcept)
      .getUserID();

    return ReportService.downloadReport(
      HashID.parseOrThrow(reportID, NotFoundException::new),
      userID,
      fileName,
      download,
      zip
    ).toResponse();
  }
}
