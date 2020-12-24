package org.veupathdb.service.multiblast.controller;

import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;

import org.gusdb.fgputil.accountdb.UserProfile;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated;
import org.veupathdb.service.multiblast.generated.model.IOBlastFormat;
import org.veupathdb.service.multiblast.generated.model.IOBlastReportField;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestJSON;
import org.veupathdb.service.multiblast.generated.model.NewJobPostRequestMultipart;
import org.veupathdb.service.multiblast.generated.resources.Jobs;
import org.veupathdb.service.multiblast.service.http.JobService;

@Authenticated
public class JobController implements Jobs
{
  private static final String AttachmentPat = "attachment; filename=\"%s.%s\"";

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
  public GetJobsByJobIdResponse getJobsByJobId(int jobId) {
    return GetJobsByJobIdResponse.respond200WithApplicationJson(
      service.getJob(jobId, user, request)
    );
  }

  @Override
  public GetJobsQueryByJobIdResponse getJobsQueryByJobId(String jobId, boolean download) {
    var head = GetJobsQueryByJobIdResponse.headersFor200();

    if (download)
      head = head.withContentDisposition(String.format(AttachmentPat, (jobId + "-query"), "txt"));

    return GetJobsQueryByJobIdResponse.respond200WithTextPlain(
      service.getQuery(jobId, user, request),
      head
    );
  }

  @Override
  public GetJobsReportByJobIdResponse getJobsReportByJobId(
    String jobId,
    IOBlastFormat format,
    List<IOBlastReportField> fields
  ) {
    var wrap = service.getReport(jobId, format, fields, user, request);
    var head = GetJobsReportByJobIdResponse.headersFor200();

    return switch (wrap.format) {
      case TABULAR, TABULARWITHCOMMENTS -> GetJobsReportByJobIdResponse.respond200WithTextPlain(
        wrap.stream,
        head.withContentDisposition(String.format(AttachmentPat, jobId, "tsv"))
      );

      case CSV -> GetJobsReportByJobIdResponse.respond200WithTextPlain(
        wrap.stream,
        head.withContentDisposition(String.format(AttachmentPat, jobId, "csv"))
      );

      case TEXTASN_1 -> GetJobsReportByJobIdResponse.respond200WithTextPlain(
        wrap.stream,
        head.withContentDisposition(String.format(AttachmentPat, jobId, "asn"))
      );

      case PAIRWISE
        , QUERYANCHOREDWITHIDENTITIES
        , QUERYANCHOREDWITHOUTIDENTITIES
        , FLATQUERYANCHOREDWITHIDENTITIES
        , FLATQUERYANCHOREDWITHOUTIDENTITIES
        , SAM
        , ORGANISMREPORT
        -> GetJobsReportByJobIdResponse.respond200WithTextPlain(
          wrap.stream,
          head.withContentDisposition(String.format(AttachmentPat, jobId, "txt"))
        );

      case ARCHIVEASN_1 -> GetJobsReportByJobIdResponse.respond200WithApplicationOctetStream(
        wrap.stream,
        head.withContentDisposition(String.format(AttachmentPat, jobId, "asna")));
      case BINARYASN_1 -> GetJobsReportByJobIdResponse.respond200WithApplicationOctetStream(
        wrap.stream,
        head.withContentDisposition(String.format(AttachmentPat, jobId, "asnb"))
      );

      case SEQALIGNJSON, MULTIFILEJSON, SINGLEFILEJSON -> GetJobsReportByJobIdResponse
        .respond200WithApplicationJson(
          wrap.stream,
          head.withContentDisposition(String.format(AttachmentPat, jobId, "json"))
        );

      case XML, MULTIFILEXML2, SINGLEFILEXML2 -> GetJobsReportByJobIdResponse
        .respond200WithApplicationXml(
          wrap.stream,
          head.withContentDisposition(String.format(AttachmentPat, jobId, "xml"))
        );
    };
  }

}
