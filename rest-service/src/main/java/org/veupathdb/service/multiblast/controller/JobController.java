package org.veupathdb.service.multiblast.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated;
import org.veupathdb.service.multiblast.generated.model.InputBlastFmtField;
import org.veupathdb.service.multiblast.generated.model.InputBlastFormat;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestJSON;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestMultipart;
import org.veupathdb.service.multiblast.generated.resources.Jobs;
import org.veupathdb.service.multiblast.service.http.JobService;

@Authenticated
public class JobController implements Jobs
{

  private final Request     request;
  private final UserProfile user;
  private final JobService  service;

  public JobController(@Context Request request) {
    this.request = request;
    this.user    = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);
    this.service = JobService.getInstance();
  }

  @Override
  public GetJobsResponse getJobs() {
    return GetJobsResponse.respond200WithApplicationJson(service.getJobs(user, request));
  }

  @Override
  public PostJobsResponse postJobs(NewJobPostRequestJSON entity) {
    return PostJobsResponse.respond200WithApplicationJson(service.createJob(entity, user, request));
  }

  @Override
  public PostJobsResponse postJobs(NewJobPostRequestMultipart entity) {
    return PostJobsResponse.respond200WithApplicationJson(service.createJob(entity, user, request));
  }

  @Override
  public GetJobsByJobIdResponse getJobsByJobId(String jobId) {
    return GetJobsByJobIdResponse.respond200WithApplicationJson(
      service.getJob(jobId, user, request)
    );
  }

  @Override
  public GetJobsReportByJobIdResponse getJobsReportByJobId(
    String jobId,
    InputBlastFormat format,
    List<InputBlastFmtField> fields
  ) {
    var wrap = service.getReport(jobId, format, fields, user, request);
    var head = GetJobsReportByJobIdResponse.headersFor200()
      .withContentDisposition("Attachment");

    return switch (wrap.format) {
      case PAIRWISE
        , QUERYANCHOREDWITHIDENTITIES
        , QUERYANCHOREDWITHOUTIDENTITIES
        , FLATQUERYANCHOREDWITHIDENTITIES
        , FLATQUERYANCHOREDWITHOUTIDENTITIES
        , TABULAR
        , TABULARWITHCOMMENTS
        , TEXTASN_1
        , CSV
        , SAM
        , ORGANISMREPORT
        -> GetJobsReportByJobIdResponse.respond200WithTextPlain(wrap.stream, head);

      case BINARYASN_1, ARCHIVEASN_1
        -> GetJobsReportByJobIdResponse.respond200WithApplicationOctetStream(wrap.stream, head);

      case SEQALIGNJSON, MULTIFILEJSON, SINGLEFILEJSON
        -> GetJobsReportByJobIdResponse.respond200WithApplicationJson(wrap.stream, head);

      case XML, MULTIFILEXML2, SINGLEFILEXML2
        -> GetJobsReportByJobIdResponse.respond200WithApplicationXml(wrap.stream, head);
    };
  }

}
