package org.veupathdb.service.multiblast.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ContainerRequest;
import org.veupathdb.lib.container.jaxrs.providers.UserProvider;
import org.veupathdb.lib.container.jaxrs.server.annotations.Authenticated;
import org.veupathdb.service.multiblast.generated.model.IOBlastReportField;
import org.veupathdb.service.multiblast.generated.model.IOJsonJobRequest;
import org.veupathdb.service.multiblast.generated.resources.Jobs;
import org.veupathdb.service.multiblast.model.io.Headers;
import org.veupathdb.service.multiblast.service.http.JobService;
import org.veupathdb.service.multiblast.util.Format;

@Authenticated(allowGuests = true)
public class JobController implements Jobs
{
  private static final Logger log = LogManager.getLogger(JobController.class);

  private static final String AttachmentPat = "attachment; filename=\"%s.%s\"";

  private final Request request;

  private final JobService  service;

  public JobController(@Context Request request) {
    log.trace("::new(request={})", request);
    this.request = request;
    this.service = JobService.getInstance();
  }

  /**
   * @return A list of jobs associated with the currently logged in user.
   */
  @Override
  public GetJobsResponse getJobs() {
    log.trace("#getJobs()");

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
  public PostJobsResponse postJobs(IOJsonJobRequest entity) {
    log.trace("#postJobs(entity={})", entity);

    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);

    return PostJobsResponse.respond200WithApplicationJson(service.createJob(entity, user));
  }

  /**
   * Create a new job with the blast query uploaded as a separate file.
   *
   * @return Basic info about the newly created job (such as the job id).
   */
  @Override
  public Response postJobs(InputStream query, InputStream config) {
    log.trace("#postJobs(query={}, config={})", query, config);

    IOJsonJobRequest props;

    try {
      props = Format.Json.readerFor(IOJsonJobRequest.class).readValue(config);
    } catch (IOException e) {
      throw new BadRequestException(e);
    }

    var user  = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);

    return Response.status(Response.Status.OK)
      .entity(service.createJob(query, props, user))
      .build();
  }

  /**
   * Attempt to retrieve a specific job associated with the logged in user.
   * <p>
   * If no job with the given ID was found, or the specified job is not
   * associated with the current user, this endpoint returns a 404.
   *
   * @param jobID ID of the job to look up.
   *
   * @return Full details about the specified job.
   */
  @Override
  public GetJobsByJobIdResponse getJobsByJobId(String jobID) {
    log.trace("#getJobByJobId(jobID={})", jobID);

    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);

    return GetJobsByJobIdResponse.respond200WithApplicationJson(service.getJob(jobID, user));
  }

  /**
   * Retrieve the raw blast query for a specific job.
   *
   * @param jobID    ID of the job whose query should be retrieved.
   * @param download Whether or not the query should be marked as an attachment
   *                 in the HTTP response.
   *
   * @return The query file associated with the specific job, either as a raw
   * text output, or a file attachment.
   */
  @Override
  public GetJobsQueryByJobIdResponse getJobsQueryByJobId(String jobID, boolean download) {
    log.trace("#getJobsQueryByJobId(jobID={}, download={})", jobID, download);

    var head = GetJobsQueryByJobIdResponse.headersFor200();

    if (download)
      head = head.withContentDisposition(String.format(AttachmentPat, (jobID + "-query"), "txt"));

    return GetJobsQueryByJobIdResponse.respond200WithTextPlain(
      service.getQuery(jobID),
      head
    );
  }

  @Override
  public Response getJobsReportByJobId(
    String jobID,
    String format,
    boolean zip,
    boolean inline,
    List<IOBlastReportField> fields
  ) {
    log.trace("#getJobsReportByJobId(jobID={}, format={}, zip={}, inline={}, fields={})", jobID, format, zip, inline, fields);

    var user = UserProvider.lookupUser(request).orElseThrow(Utils::noUserExcept);

    var maxDlSizeStr = ((ContainerRequest)request).getHeaderString(Headers.ContentMaxLength);
    var maxDlSize = maxDlSizeStr == null ? null : Long.parseLong(maxDlSizeStr);

    var wrap = service.getReport(jobID, user.getUserID(), format, zip, fields, maxDlSize);

    var resp = Response.status(200).header("Content-Type", wrap.contentType);

    if (!inline)
      resp.header("Content-Disposition", String.format(AttachmentPat, "report", wrap.ext));

    return resp.entity(wrap.stream).build();
  }
}
