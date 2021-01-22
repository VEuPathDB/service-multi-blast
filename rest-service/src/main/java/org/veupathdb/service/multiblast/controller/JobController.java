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

  private final Request request;

  private final JobService  service;

  public JobController(@Context Request request) {
    this.request = request;
    this.service = JobService.getInstance();
  }

  /**
   * @return A list of jobs associated with the currently logged in user.
   */
  @Override
  public GetJobsResponse getJobs() {
    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);
    return GetJobsResponse.respond200WithApplicationJson(service.getJobs(user));
  }

  /**
   * Create a new job by JSON body.
   *
   * @param entity New job request parameters.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @Override
  public PostJobsResponse postJobs(NewJobPostRequestJSON entity) {
    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);
    return PostJobsResponse.respond200WithApplicationJson(service.createJob(entity, user));
  }

  /**
   * Create a new job with the blast query uploaded as a separate file.
   *
   * @param entity New job request parameters.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @Override
  public PostJobsResponse postJobs(NewJobPostRequestMultipart entity) {
    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);
    return PostJobsResponse.respond200WithApplicationJson(service.createJob(entity, user));
  }

  /**
   * Attempt to retrieve a specific job associated with the logged in user.
   * <p>
   * If no job with the given ID was found, or the specified job is not
   * associated with the current user, this endpoint returns a 404.
   *
   * @param jobId ID of the job to look up.
   *
   * @return Full details about the specified job.
   */
  @Override
  public GetJobsByJobIdResponse getJobsByJobId(String jobId) {
    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);
    return GetJobsByJobIdResponse.respond200WithApplicationJson(
      service.getJob(jobId, user)
    );
  }

  /**
   * Retrieve the raw blast query for a specific job.
   *
   * @param jobId    ID of the job whose query should be retrieved.
   * @param download Whether or not the query should be marked as an attachment
   *                 in the HTTP response.
   *
   * @return The query file associated with the specific job, either as a raw
   * text output, or a file attachment.
   */
  @Override
  public GetJobsQueryByJobIdResponse getJobsQueryByJobId(String jobId, boolean download) {
    var head = GetJobsQueryByJobIdResponse.headersFor200();

    if (download)
      head = head.withContentDisposition(String.format(AttachmentPat, (jobId + "-query"), "txt"));

    return GetJobsQueryByJobIdResponse.respond200WithTextPlain(
      service.getQuery(jobId),
      head
    );
  }

  @Override
  public GetJobsReportByJobIdResponse getJobsReportByJobId(
    String jobId,
    String format,
    List<IOBlastReportField> fields
  ) {
    var wrap = service.getReport(jobId, format, fields);
    var head = GetJobsReportByJobIdResponse.headersFor200();

    head.withContentDisposition(String.format(AttachmentPat, "report", "zip"));

    return GetJobsReportByJobIdResponse.respond200WithApplicationZip(wrap, head);
  }
}
